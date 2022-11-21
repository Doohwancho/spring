package com.cho.example.databinding.step1;

import com.cho.example.databinding.Event;

import java.beans.PropertyEditorSupport;

//case1)
//PropertyEditor는 Spring 3.0 이전까지 DataBinder가 변환 작업에 사용한 인터페이스
//단점1. thread safe하지 못하다
//단점2. Object <-> String간 변환만 가능해서 사용 범위가 제한적이다.
public class EventEditor  extends PropertyEditorSupport {

    @Override
    public String getAsText(){
        Event event = (Event)getValue();
        return event.getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Event event = new Event();
        event.setId(Integer.parseInt(text));
        setValue(event);
    }
}
