package org.zerock.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect //해당 클래스의 객체가 Aspect로 구현된 것임으로 나타내기 위해 사용
@Log4j
@Component //aop랑 상관은 없지만, 스프링에서 빈으로 인식하기 위해 사용
public class LogAdvice {

//	@Before("execution(* org.zerock.service.SampleService*.*(..))") //AspectJ
//	public void logBefore() {
//		log.info("==========^^^^^^^^^^^^^^^^^^^^^^^");
//	}
	
	@Before("execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1, str2)") //AspectJ - checking params
	public void logBeforeWithParam(String str1, String str2) {
		log.info("???????????????????");
		log.info("str1: "+str1);
		log.info("str2: "+str2);
	}
}
