package com.cho.security3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/* 이 프로젝트에서는 기존 strict한 CORS정책에서 벗어나기 위해 모든걸 허용한다.*/
@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true); //내 서버가 응답할 때, json을 자바스크립트에서 처리할 수 있게 설정하는 것. 이게 false면 js axiom등으로 요청와도 response가 안감. 
      config.addAllowedOrigin("*"); // 모든 ip에 응답하겠다. e.g. http://domain1.com
      config.addAllowedHeader("*"); // 모든 header에 응답을 허용하겠다. 
      config.addAllowedMethod("*"); // 모든 post, get, delete, put, patch, 요청을 허용하겠다 라는 뜻. 

      source.registerCorsConfiguration("/api/**", config);
      return new CorsFilter(source);
   }

}
