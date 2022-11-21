package com.cho.example.environment.applicationEventPublisher;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyObserver1 {

    @EventListener
    public void handler(Event event){
        System.out.println(event.getMessage() + ", I'm cho.");
    }
}
