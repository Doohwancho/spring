package com.cos.person.aop.step1exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import io.sentry.Sentry;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestController
@ControllerAdvice
public class MyExceptionHandler {

    //TODO - step1
    //exception 낚아채어 에러 핸들링 여기서 함. 컨트롤러에서 복잡하게 안하고. 개꿀!
    @ExceptionHandler(value=IllegalArgumentException.class)
    public String argumentException(IllegalArgumentException e) {
        Sentry.captureException(e);
        return "오류 : "+e.getMessage();
    }
}
