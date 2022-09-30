package com.cho.annotation.aop;

// @Controller, @RestController, @Component, @Configuration


import com.cho.annotation.domain.CommonDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//실행 순서가, 1. @Configuration 2. @Controller, @RestController, @Service, etc 3. @Component.
//AOP는 UserController 빈이 등록된 이후에 reflection으로 돌면서 찾는거라, @Component로 빈을 띄운다.
@Component
@Aspect //Aspect를 공통기능이라고 우리는 부른다.
public class BindingAdvice {

    private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

//    // 어떤함수가 언제 몇번 실행됐는지 횟수같은거 로그 남기기
//    @Before("execution(* com.cos.person.web..*Controller.*(..))")
//    public void testCheck() {
//        //request 값 처리 못하나요?
//        HttpServletRequest request =
//                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        System.out.println("주소 : "+request.getRequestURI()); // "/user"
////        log.info("주소 : "+request.getRequestURI());
//
//        System.out.println("전처리 로그를 남겼습니다.");
////        log.info("전처리 로그를 남겼습니다.");
//
//    }
//
//    @After("execution(* com.cos.person.web..*Controller.*(..))")
//    public void testCheck2() {
//
//        System.out.println("후처리 로그를 남겼습니다.");
//
//    }


    // 함수 : 앞 뒤
    // 함수 : 앞 (username이 안들어왔으면 내가 강제로 넣어주고 실행)
    // 함수 : 뒤 (응답만 관리)

    /*

    @Valid @RequestBody JoinReqDto dto, BindingResult bindingResult 에서

    JoinReqDto의 @Valid 조건 체크해서 에러나면,
    에러 결과값을 BindingResult에 담아서,
    해당 에러 메시지 출력, 처리를 여기서 함

     */

    //@Around는 JoinPoint, com.cos.person.web..*Controller.*(..))는 PointCut. 이 Aspect를 포인트컷에 적용하는 행위 전체를 Weaving이라고 한다.
    //왜 @Around를 이 메서드에 쓰냐면, @Valid 검사해서 에러나면, 컨트롤러 메서드 실행 직후에, 이 validCheck() AOP가 에러메시지 대신 반환하게 하기 위해.


    //@Before
    //@After
    @Around("execution(* com.cos.person.web..*Controller.*(..))") //맨 마지막 .*(.) 에서 ()안 점의 갯수는 인자의 갯수를 뜻함. 두개는 인자 갯수 상관 없다는 뜻.
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();

        System.out.println("type : "+type); //com.cos.person.web.UserController
        System.out.println("method : "+method); //findAll

        Object[] args = proceedingJoinPoint.getArgs();

        for (Object arg : args) {
            if(arg instanceof BindingResult) { //BindingResult가 붙어있으면,
                BindingResult bindingResult = (BindingResult) arg; //BindingResult로 다운캐스팅한 이후,

                // 서비스 : 정상적인 화면 -> 사용자요청
                if(bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for(FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        // 로그 레벨 error, warn, info, debug
                        log.warn(type+"."+method+"() => 필드 : "+error.getField()+", 메시지 : "+error.getDefaultMessage()); //에러 메시지는 JoinReqDto에 @Size(max = 20, message = "유저네임 길이를 초과하였습니다.") 내가 적은 에러가 출력됨
                        log.debug(type+"."+method+"() => 필드 : "+error.getField()+", 메시지 : "+error.getDefaultMessage());
//                        Sentry.captureMessage(type+"."+method+"() => 필드 : "+error.getField()+", 메시지 : "+error.getDefaultMessage());

                        //DB연결 -> DB남기기
                        //File file = new File();
                    }

                    return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed(); // 에러 없었어! 함수의 스택을 실행해!
    }
}
