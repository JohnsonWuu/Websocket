package com.utils;

import com.data.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {

    private static final String LANGUAGE = "language";

    @Value("${mix.jwt.expirationInMs}")
    private int jwtExpirationInMs;

    private final KeyUtil keyUtil;

    public Claims getAllClaims(String token, JwtUsage jwtUsage) {

        PublicKey publicKey = getPublicKey(jwtUsage);

        return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();
    }

    public AuthResponse generateMixJwt(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            claims.put("isAdmin", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            claims.put("isUser", true);
        }

        PrivateKey privateKey = keyUtil.getMixPrivateKey();

        return new AuthResponse(doGenerateToken(claims, userDetails.getUsername(), privateKey));
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, PrivateKey privateKey) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    private Map<String, Object> createClaims(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            claims.put("isAdmin", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            claims.put("isUser", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_SYSTEM"))) {
            claims.put("isSystem", true);
        }

        return claims;
    }

    public UserDetails createTplrsUserDetails() {
        String userName = "tplrs";
        String password = "1234";
        List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_SYSTEM"));

        return new User(userName, password, roles);
    }

    private PublicKey getPublicKey(JwtUsage jwtUsage) {
        PublicKey publicKey;
        switch (jwtUsage) {
            case MIX:
                publicKey = keyUtil.getMixPublicKey();
                break;
            case MMS:
                publicKey = null;
                break;
            default:
                publicKey = null;
                break;
        }
        return publicKey;
    }

    public boolean validateToken(String token, JwtUsage jwtUsage) {

        PublicKey publicKey = getPublicKey(jwtUsage);

        boolean success = false;
        try {
            Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token);
            success = true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw ex;
        }

        return success;
    }

    public String getSubjectFromToken(String token, JwtUsage jwtUsage) {

        Claims claims = getAllClaims(token, jwtUsage);

        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token, JwtUsage jwtUsage) {

        List<SimpleGrantedAuthority> roles = null;

        Claims claims = getAllClaims(token, jwtUsage);
        Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        Boolean isUser = claims.get("isUser", Boolean.class);

        if (isAdmin != null && isAdmin == true) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        if (isUser != null && isUser == true) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }

        roles.stream().forEach( s-> System.out.println("getAuthorityL: " + s.getAuthority()));

        return roles;
    }

}
