package org.springframework.amqp.helloworld.error;

import org.slf4j.Logger;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ErrorHandler;

/*
    Q. what is this?
    
    A. This sample demonstrates how to add a global error handler to `@RabbitListener` methods.
    It uses an auto-delete queue that will be removed after the demo completes.
    
    The error handler is the same as the default (`ConditionalRejectingErrorHandler`) but with a custom `FatalExceptionStrategy`
    that extends the default and logs the failed message.
    
    Q. how this code works?
    A. A JSON message converter is used; the sample sends one "good" message, and one bad one, which fails message conversion.
    Message conversion is considered fatal and the message is not requeued.

    Q. reminder
    A. NOTE: Such a simple handler is not generally needed since the framework will log the failed message itself; for a real
    application, some additional action might be taken, for example write the bad message to a database.

 */
@SpringBootApplication
public class GlobalErrorHandlerApplication {
    
    private static final String TEST_QUEUE = "spring.amqp.global.error.handler.demo";
    
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
    
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(GlobalErrorHandlerApplication.class, args);
        context.getBean(GlobalErrorHandlerApplication.class).runDemo(context.getBean(RabbitTemplate.class));
        context.close();
    }
    
    private void runDemo(RabbitTemplate template) throws Exception {
        template.convertAndSend(TEST_QUEUE, new Foo("bar")); //first, send good message
        template.convertAndSend(TEST_QUEUE, new Foo("bar"), m -> { //second, send bad message, which fails at message conversion
            return new Message("some bad json".getBytes(), m.getMessageProperties());
        });
        Thread.sleep(5000);
    }
    
    @RabbitListener(queues = TEST_QUEUE)
    public void handle(Foo in) {
        logger.info("Received: " + in);
    }
    
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
        SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setErrorHandler(errorHandler()); //set custom error handler!
        return factory;
    }
    
    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy()); //uses custom error handler!
    }
    
    @Bean
    public Queue queue() {
        return new Queue(TEST_QUEUE, false, false, true);
    }
    
    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    //custom error handler
//    NOTE: Such a simple handler is not generally needed since the framework will log the failed message itself; for a real
//    application, some additional action might be taken, for example write the bad message to a database.
    public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
        
        private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
        
        @Override
        public boolean isFatal(Throwable t) {
            if (t instanceof ListenerExecutionFailedException) {
                ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
                logger.error("Failed to process inbound message from queue "
                    + lefe.getFailedMessage().getMessageProperties().getConsumerQueue()
                    + "; failed message: " + lefe.getFailedMessage(), t);
            }
            return super.isFatal(t);
        }
        
    }
    
    public static class Foo {
        
        private String foo;
        
        public Foo() {
            super();
        }
        
        public Foo(String foo) {
            this.foo = foo;
        }
        
        public String getFoo() {
            return this.foo;
        }
        
        public void setFoo(String foo) {
            this.foo = foo;
        }
        
        @Override
        public String toString() {
            return "Foo [foo=" + this.foo + "]";
        }
        
    }
}
