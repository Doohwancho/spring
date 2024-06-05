package com.springboot.fundamental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*

@SpringBootApplication은 아래 3개의 합
1. @Configuration
2. @ComponentScan -> 현재 패키지를 기준으로 그 아래 패키지까지 찾아내서 스프링 빈으로 등록하는 기능을 가진 어노테이션
3. @EnableAutoConfiguration -> 스프링 빈들을 자동적으로 컨테이너에 등록하는 역할을 하는 어노테이션

 */
@SpringBootApplication
public class FundamentalApplication {

	public static void main(String[] args) {

		SpringApplication.run(FundamentalApplication.class, args); //ApplicationContext(스프링 컨테이너) 생성
	}

}
