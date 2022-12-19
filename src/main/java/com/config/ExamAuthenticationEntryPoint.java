package com.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.result.ResultData;
import com.result.ReturnCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExamAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String EXPIRE_EXCEPTION = "expire_exception";
    private static final String AUTHENTICATION_EXCEPTION = "authentication_exception";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        int errorCode = getErrorCodeByAttribute(request);
        System.out.println("ExamAuthenticationEntryPoint,errorCode: " + errorCode + " ,authException msg: " + authException.getMessage());

        Exception exception = getExceptionByByAttribute(request);
        String errorMessage = getErrorMessageFromException(exception);

        if (errorMessage == null || errorMessage.isEmpty()) {
            errorMessage = ReturnCode.AUTHENTICATION_ERROR.getMessage();
        }

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(mapper.writeValueAsString(new ResultData<String>(
                errorCode,
                errorMessage,
                null)));
    }

    private Exception getExceptionByByAttribute(HttpServletRequest request) {

        Exception exception = (Exception) request.getAttribute(AUTHENTICATION_EXCEPTION);

        if (exception == null) {
            exception = (Exception) request.getAttribute(EXPIRE_EXCEPTION);
        }

        return exception;
    }

    private String getErrorMessageFromException(Exception exception) {

        String errorMessage = null;

        if (exception != null) {
            if (exception.getCause() != null) {
                errorMessage = " ,exception.getCause: " + exception.getCause().toString() + " ,exception.getMessage: " + exception.getMessage();
            } else {
                errorMessage = exception.getMessage();
            }
        }

        System.out.println("getErrorMessageFromException,errorMessage: " + errorMessage);
        return errorMessage;
    }

    private int getErrorCodeByAttribute(HttpServletRequest request) {

        Exception exception = (Exception) request.getAttribute(EXPIRE_EXCEPTION);
        if (exception != null) {
            return ReturnCode.TOKEN_EXPIRE_ERROR.getCode();
        }

        return ReturnCode.AUTHENTICATION_ERROR.getCode();
    }
}