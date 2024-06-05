package com.cho.example.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz){ //주어진 객체가 지원이가능한지 파악하는 메서드
        return Event.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){ //target 객체 유효성 검증.

        //case1) custom으로 만들어 검증
        Event event = (Event)target;

        if(isEmptyOrWhitespace(event.getTitle())){
            errors.rejectValue("title","empty");
        }

        //case2) ValidationUtils 메서드 이용
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty", "title 가 empty");
    }

    public boolean isEmptyOrWhitespace(String value){ //검증 메서드. value가 null인지, 공백 제거한 길이가 0인지 검사하는 메서드.
        if(value == null || value.trim().length() == 0){
            return true;
        }
        return false;
    }
}
