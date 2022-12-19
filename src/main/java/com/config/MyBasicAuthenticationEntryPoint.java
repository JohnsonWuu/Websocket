package com.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.result.ResultData;
import com.result.ReturnCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        System.out.println("Enter MyBasicAuthenticationEntryPoint");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.addHeader("WWW-Authenticate", "Basic realm= + getRealmName() + ");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(mapper.writeValueAsString(new ResultData<String>(
                ReturnCode.AUTHENTICATION_ERROR.getCode(),
                ReturnCode.AUTHENTICATION_ERROR.getMessage(),
                null)));
    }

    @Override
    public void afterPropertiesSet(){
        setRealmName("spring");
        super.afterPropertiesSet();
    }
}
