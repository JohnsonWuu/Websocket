package com.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.result.ResultData;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExamAccessDeniedHandler implements AccessDeniedHandler {

    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException e) throws IOException, ServletException {

        System.out.println("ExamAccessDeniedHandler,errorCode");

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(mapper.writeValueAsString(new ResultData<String>(
                1000,
                "Access Denied",
                null)));

    }
}