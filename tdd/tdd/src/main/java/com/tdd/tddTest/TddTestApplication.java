package com.tdd.tddTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //jpa로 db에 CRUD한 시간 기록하는 모듈. BaseTimeEntity.java의 @CreatedDate, @LastModifiedDate로 시간 체크.
@SpringBootApplication
public class TddTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TddTestApplication.class, args);
	}

}
