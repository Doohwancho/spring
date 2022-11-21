package com.cho.example.databinding.step3;

import com.cho.example.databinding.Event;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

//case3)
@Component
public class EventFormatter implements Formatter<Event> {
    @Override
    public Event parse(String text, Locale loacle) throws ParseException {
        return new Event(Integer.parseInt(text));
    }

    @Override
    public String print(Event object, Locale locale){
        return object.getId().toString();
    }
}
