package com.controller;

import com.data.AuthRequest;
import com.data.AuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public AuthResponse authenticate(HttpServletRequest request, @Valid @RequestBody AuthRequest authenticationRequest) throws JsonProcessingException {

        log.info("API: /authenticate AuthRequest : {}", authenticationRequest);

        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getUserPassword();

        //TODO get system name from request

        //TODO check user if valid or not

        //TODO get UserDetails
        List<SimpleGrantedAuthority> roles = null;
        roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails userDetails = new User(userName, password, roles);

        //TODO generate token by system

        return jwtUtil.generateMixJwt(userDetails);
    }

    @PostMapping("/authenticate2")
    public AuthResponse authenticate2(HttpServletRequest request, @Valid @RequestBody AuthRequest authenticationRequest) throws JsonProcessingException {

        log.info("API: /authenticate2 AuthRequest : {}", authenticationRequest);

        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getUserPassword();

        //TODO get system name from request

        //TODO check user if valid or not

        //TODO get UserDetails
        List<SimpleGrantedAuthority> roles = null;
        roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new User(userName, password, roles);

        //TODO generate token by system

        return jwtUtil.generateMixJwt(userDetails);
    }
}