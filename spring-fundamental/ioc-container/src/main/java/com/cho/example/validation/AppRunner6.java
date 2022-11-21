package com.cho.example.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Component
public class AppRunner6 implements ApplicationRunner {

    @Autowired
    @Qualifier("defaultValidator")
    Validator validator;

    public void run(ApplicationArguments args) throws Exception {
        Event event = new Event();
        event.setId(-1);
        event.setTitle("");

        //error 검증
        Errors errors = new BeanPropertyBindingResult(event, "event");
        validator.validate(event, errors);

        //error 출력
        System.out.println(errors.hasErrors());
        errors.getAllErrors().forEach(e -> {
            System.out.println("============ ERROR ===========");
            Arrays.stream(e.getCodes()).forEach(System.out::println);
            System.out.println(e.getDefaultMessage());
        });
    }
    /*
    error log

    true
    ============ ERROR ===========
    Min.event.id
    Min.id
    Min.java.lang.Integer
    Min
    0 이상이어야 합니다
    ============ ERROR ===========
    NotBlank.event.title
    NotBlank.title
    NotBlank.java.lang.String
    NotBlank
    공백일 수 없습니다

     */

}
