package com.cho.example.config;

import com.cho.example.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //.addPathPatterns("/**");
    //.excludePathPatterns("/user/*")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/board/**")
                .excludePathPatterns("/board/list")
                .excludePathPatterns("/board/detail/**");
        //registry.addInterceptor(new AdminInterceptor())
        //.addPathPatterns("/admin/**");
    }
}