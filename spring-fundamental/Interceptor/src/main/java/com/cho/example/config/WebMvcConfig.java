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
                .addPathPatterns("/board/**") //이 url로 오는 컨트롤러에만 interceptor를 붙인다.
                .excludePathPatterns("/board/list") //이 url들은 제외한다. (로그인 안해도 접근 가능)
                .excludePathPatterns("/board/detail/**");
        //registry.addInterceptor(new AdminInterceptor())
        //.addPathPatterns("/admin/**");
    }
}