package com.example.webscoket;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import zipkin2.server.internal.EnableZipkinServer;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableBatchProcessing
//@EnableZipkinServer
@ComponentScan({"com.config","com.controller","com.listener","com.exception","com.service","com.event","com.utils","com.data"})
@EnableJpaRepositories(basePackages = {"com.repository"})
@EntityScan(basePackages = {"com.entity"})
public class WebscoketApplication {

    private static String TEST_STR;

    public static void main(String[] args) {
        SpringApplication.run(WebscoketApplication.class, args);

        System.out.println("Enter Main function...");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propsConfig
                = new PropertySourcesPlaceholderConfigurer();
        propsConfig.setLocation(new ClassPathResource("git.properties"));
        propsConfig.setIgnoreResourceNotFound(true);
        propsConfig.setIgnoreUnresolvablePlaceholders(true);
        return propsConfig;
    }

    @PostConstruct
    public void postConstruct(){

        System.out.println("postConstruct...,TEST_STR: " + TEST_STR);
    }

    @Bean
    void test()
    {
        TEST_STR = "test str";
        System.out.println("Init test() bean ,TEST_STR: " + TEST_STR);
    }
}
