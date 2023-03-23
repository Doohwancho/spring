package com.sp.sec2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class UserAuthorityApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAuthorityApplication.class, args);
    }

}