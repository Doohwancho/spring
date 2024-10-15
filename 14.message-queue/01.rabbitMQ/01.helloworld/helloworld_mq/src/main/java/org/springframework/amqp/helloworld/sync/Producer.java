package org.springframework.amqp.helloworld.sync;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Producer {
    
    //case1) message를 1번만 보내는 코드
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
//        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
//        amqpTemplate.convertAndSend("Hello World");
//        System.out.println("Sent: Hello World");
//    }
    
    
    //case2) message를 매 seconds간 보내는 코드
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
    
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            amqpTemplate.convertAndSend("Hello World");
            System.out.println("Sent: Hello World");
        }, 0, 1, TimeUnit.SECONDS);
    }
}