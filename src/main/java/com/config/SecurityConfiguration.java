package com.config;

import com.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Autowired
    private ExamAuthenticationEntryPoint examAuthenticationEntryPoint;

    @Autowired
    private ExamAccessDeniedHandler examAccessDeniedHandler;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private JwtUtil jwtTokenUtil;

   // @Autowired
    //private JwtRequestFilter jwtRequestFilter;

    private static final String[] WHITELIST = {
            "/authenticate",
            "/authenticate2",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/**",
            "/webjars/**",
            "/zipkin/**"
    };
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        System.out.println("AuthenticationManagerBuilder...");

        //链式写法
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("123")
//                .roles("ADMIN")
//                //.roles("admin_role")
//                .and()
//                .withUser("user2")
//                .password("123")
//                .roles("ADMIN2");

//
//    }
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfiguration...e" );

//        http.
//                httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/helloo").hasRole("admin_role");
                //.antMatchers("/delete").hasRole("admin_role")
                //.antMatchers("/details").hasAnyRole("admin_role","student")
                //.and()
                //.formLogin();

        //http.authorizeRequests().antMatchers("/hello2").access("hasAuthority('ROLE_ADMIN')");

        //Can work
        //http.httpBasic().and().authorizeRequests().antMatchers("/helloo").hasRole("ADMIN");

        //Cannot work
        //http.httpBasic().and().authorizeRequests().antMatchers("/helloo").hasAuthority("ADMIN");

        //Can work
        //http.httpBasic().and().authorizeRequests().antMatchers("/helloo").hasAuthority("ROLE_ADMIN");


//        http.authorizeRequests().antMatchers("/helloo").hasRole("ADMIN");

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/helloo").access("hasAuthority('ROLE_ADMIN')");

        //Can work
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
   //             .antMatchers("/helloo")
  //              .access("hasAuthority('ROLE_ADMIN')")
 //               .antMatchers(WHITELIST).permitAll()
                .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(examAccessDeniedHandler)
                    .authenticationEntryPoint(examAuthenticationEntryPoint);
        http.addFilterBefore(new JWTLoginFilter ("/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtRequestFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(WHITELIST);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定義的驗證
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
