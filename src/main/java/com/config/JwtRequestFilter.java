package com.config;

import com.utils.JwtUsage;
import com.utils.JwtUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

//@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String EXPIRE_EXCEPTION = "expire_exception";
    private static final String AUTHENTICATION_EXCEPTION = "authentication_exception";

    private JwtUtil jwtTokenUtil;

    public JwtRequestFilter(JwtUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }


//    private static final String[] WHITELIST = {
//            "/authenticate",
//            "/authenticate2",
//            "/swagger-ui.html",
//            "/swagger-ui/**",
//            "/swagger-resources/**",
//            "/v2/api-docs",
//            "/v3/api-docs",
//            "/v3/**",
//            "/webjars/**",
//            "/zipkin/**"
//    };

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//
//        boolean contains = Arrays.stream(WHITELIST).anyMatch(request.getServletPath()::equals);
//
//        return contains;
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {


        try {
            String jwtToken = extractJwtToken(request);

            System.out.println("doFilterInternal function,jwtToken: "+ jwtToken + " ,jwtTokenUtil: " + jwtTokenUtil);

            if (StringUtils.isNotEmpty(jwtToken) && jwtTokenUtil.validateToken(jwtToken, JwtUsage.MIX)) {

                System.out.println("doFilterInternal function,jwtToken is valid");
                UserDetails userDetails = new User(jwtTokenUtil.getSubjectFromToken(jwtToken, JwtUsage.MIX), "",
                        jwtTokenUtil.getRolesFromToken(jwtToken, JwtUsage.MIX));

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                log.error("Cannot set the Security Context");
                throw  new BadCredentialsException("BAD");
            }

        } catch(ExpiredJwtException ex) {
            log.error("ExpiredJwtException");
            request.setAttribute(EXPIRE_EXCEPTION, ex);
            throw ex;
        } catch(BadCredentialsException ex) {
            log.error("BadCredentialsException occurred");
            request.setAttribute(AUTHENTICATION_EXCEPTION, ex);
            throw ex;
        }

        chain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest request) {

        final String requestTokenHeader = request.getHeader("Authorization");

        if (StringUtils.isNotEmpty(requestTokenHeader) && requestTokenHeader.toLowerCase().startsWith("bearer")) {
            return requestTokenHeader.substring(7);
        }

        return null;
    }
}
