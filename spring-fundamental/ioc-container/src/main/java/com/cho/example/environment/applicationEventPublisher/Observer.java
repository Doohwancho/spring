package com.cho.example.environment.applicationEventPublisher;

import org.springframework.context.event.EventListener;

public class Observer {

    @EventListener
    public void onApplicationEvent(Event event){
        System.out.println("Event: " + event.getMessage());
    }
}
