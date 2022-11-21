package com.cho.example.databinding.step2;

import com.cho.example.databinding.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//case2)
public class EventConverter {
    @Component
    public static class StringToEvent implements Converter<String, Event> { //Event 자리에 반환될 객체를 입력해 주면 됨
        @Override
        public Event convert(String source){
            return new Event(Integer.parseInt(source));
        }
    }

    @Component
    public static class EventToString implements Converter<Event, String>{ //Event 자리에 반환될 객체를 입력해 주면 됨
        @Override
        public String convert(Event source){
            return source.getId().toString();
        }
    }
}
