package org.springframework.amqp.helloworld.router_aka_exchange.confirm;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.connection.CorrelationData.Confirm;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class SpringRabbitConfirmsReturnsApplication {
    
    private static final String QUEUE = "spring.publisher.sample";
    
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SpringRabbitConfirmsReturnsApplication.class,
            args);
        context.close();
    }
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    private final CountDownLatch listenLatch = new CountDownLatch(1);
    
    private void runDemo() throws Exception {
        //step0) setup callbacks?
        setupCallbacks();
        CorrelationData correlationData;
        Confirm confirm;
        
        //case1) 메시지를 올바른 queue에 올바른 router(default exchange "")에 보내는 것
        /*
        Confirm received for good delivery, ack = true
        Received  ack for correlation: CorrelationData [id=Correlation for message 1]
        Listener received: foo
        Message received by listener
         */
        correlationData = new CorrelationData("Correlation for message 1");
        this.rabbitTemplate.convertAndSend("", QUEUE, "foo", correlationData); //첫번째 파라미터 ""는 default exchange(router)로 보내는 것.

        confirm = correlationData.getFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Confirm received for good delivery, ack = " + confirm.isAck());
        if (this.listenLatch.await(10, TimeUnit.SECONDS)) {
            System.out.println("Message received by listener");
        }
        else {
            System.out.println("Message NOT received by listener");
        }
        
//        case2) 메시지를 wrong queue에 올바른 default router(exchange)인 ""로 보내는 것
        //흥미로운점: 이상한 queue로 보냈는데, producer는 ack=true를 받았다. 왜? queue에 가기전에 router인 "" exchange한테는 잘 갔거든. 그래서 ack=true 받은 것.
        //         producer -> router("" exchange) -> queue 에 갈때, queue에 못갔으니까,  confirm으로 NO_ROUTE 에러 확인하는 것
        /*
        Message after conversion: (Body:'bar' MessageProperties [headers={}, contentType=text/plain, contentEncoding=UTF-8, contentLength=3, deliveryMode=PERSISTENT, priority=0, deliveryTag=0])
        Confirm received for send to missing queue, ack = true
        Returned: (Body:'bar' MessageProperties [headers={spring_returned_message_correlation=Correlation for message 2}, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, deliveryTag=0])
        replyCode: 312
        replyText: NO_ROUTE
        exchange/rk: /spring.publisher.samplespring.publisher.sample
        Return received:ReturnedMessage [message=(Body:'bar' MessageProperties [headers={spring_listener_return_correlation=34453ddd-f3da-4896-887c-20001a8128ce, spring_returned_message_correlation=Correlation for message 2}, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, redelivered=false, receivedExchange=, receivedRoutingKey=spring.publisher.samplespring.publisher.sample, deliveryTag=0]), replyCode=312, replyText=NO_ROUTE, exchange=, routingKey=spring.publisher.samplespring.publisher.sample]
        Received  ack for correlation: CorrelationData [id=Correlation for message 2]
        */
        correlationData = new CorrelationData("Correlation for message 2");
        this.rabbitTemplate.convertAndSend("", "WRONG_QUEUE_DESTINATION", "bar", message -> { //wrong queue에 올바른 default router(exchange)인 ""로 보내는 것
            System.out.println("Message after conversion: " + message);
            return message;
        }, correlationData);

        confirm = correlationData.getFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Confirm received for send to missing queue, ack = " + confirm.isAck());
        System.out.println("Return received:"  + correlationData.getReturned());
        
        
        
//        //case3) 메시지를 올바른 queue로 보내는데 exchange(router)를 이상한 곳으로 보내는 코드 (nack received)
        //producer -> router("" exchange) -> queue -> consumer 이 순으로 가는데,
        //router에 못가면, ack=false로 처리됨
        //보통 문제가 되는건 case2인 듯. queue에 도달 못했는데 ack=true로 오잖아. 이 경우 따로 exception 처리 해줘야 하는 듯
        /*
            Confirm received for send to missing exchange, ack = false
            Received  nack for correlation: CorrelationData [id=Correlation for message 3]
         */
        correlationData = new CorrelationData("Correlation for message 3");
        // send to non-existent exchange - expect nack
        this.rabbitTemplate.convertAndSend(UUID.randomUUID().toString(), QUEUE, "baz", correlationData); //올바른 queue로 보내는데 exchange(router)를 이상한 곳으로 보내는 코드

        confirm = correlationData.getFuture().get(10, TimeUnit.SECONDS);
        System.out.println("Confirm received for send to missing exchange, ack = " + confirm.isAck());
    }
    
    private void setupCallbacks() {
        /*
         * Confirms/returns enabled in application.properties - add the callbacks here.
         */
        this.rabbitTemplate.setConfirmCallback((correlation, ack, reason) -> {
            if (correlation != null) {
                System.out.println("Received " + (ack ? " ack " : " nack ") + "for correlation: " + correlation);
            }
        });
        this.rabbitTemplate.setReturnsCallback(returned -> {
            System.out.println("Returned: " + returned.getMessage() + "\nreplyCode: " + returned.getReplyCode()
                + "\nreplyText: " + returned.getReplyText() + "\nexchange/rk: "
                + returned.getExchange() + "/" + returned.getRoutingKey());
        });
    }
    
    
    @Bean
    public ApplicationRunner runner() {
        return args -> runDemo();
    }
    
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false, false, true);
    }
    
    @RabbitListener(queues = QUEUE)
    public void listen(String in) {
        System.out.println("Listener received: " + in);
        this.listenLatch.countDown();
    }
    
}
