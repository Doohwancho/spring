package org.springframework.amqp.helloworld.sync;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Consumer {
    
    //case1) message를 1번만 보내는 코드
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
//        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
//        System.out.println("Received: " + amqpTemplate.receiveAndConvert());
//    }
    
    
    //case2) message를 매 seconds간 보내는 코드
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
    
        while (true) {
            Object message = amqpTemplate.receiveAndConvert();
            if (message != null) {
                System.out.println("Received: " + message);
            }
            try {
                Thread.sleep(1000); // Wait for 1 second before checking for new messages
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}