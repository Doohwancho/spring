package com.cos.person.aop.step5logRequestURI;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    //TODO - step5
    //controller 실행 전, request의 URI를 로그로 남기는 AOP
    @Before("execution(* com.cos.person.web..*Controller.*(..))")
    public void testCheck() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("주소 : "+request.getRequestURI());
    }

    //TODO - diff @After @AfterRunning
    //@After는 Target 실행 후 무조건 실행
    //@AfterRunning은 Target 실행 후 정상적으로 실행되었을 때에만 실행
    @After("execution(* com.cos.person.web..*Controller.*(..))")
    public void testCheck2() {
        log.info("후처리 로그를 남겼습니다.");
    }


    // 함수 : 앞 뒤
    // 함수 : 앞 (username이 안들어왔으면 내가 강제로 넣어주고 실행)
    // 함수 : 뒤 (응답만 관리)
}
