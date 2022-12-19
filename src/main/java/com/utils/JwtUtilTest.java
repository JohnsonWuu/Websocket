package com.utils;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtUtilTest {

    static final long EXPIRATIONTIME = 432_000_000;     // 5天
    static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
    //static final Key key = MacProvider.generateKey();	//給定一組密鑰，用來解密以及加密使用



    // JWT產生方法
    public static void addAuthentication(HttpServletResponse response, Authentication user) {

        System.out.println("Call addAuthentication");
    }

    // JWT驗證方法
    public static Authentication getAuthentication(HttpServletRequest request) {

        System.out.println("Call getAuthentication");
        return null;
    }
}
