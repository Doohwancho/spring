package com.example.step0what;

import java.util.Arrays;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableBatchProcessing
public class SimpleBatch {
    
    @Bean
    public ItemReader<String> firstSimpleReader() {
        return new ListItemReader<>(Arrays.asList("one", "two", "three"));
    }
    
    @Bean
    public ItemProcessor<String, String> firstSimpleProcessor() {
        return item -> item.toUpperCase();
    }
    
    @Bean
    public ItemWriter<String> firstSimpleWriter() {
        return items -> items.forEach(System.out::println);
    }
    
    @Bean
    public Step firstStep1(StepBuilderFactory stepBuilderFactory, ItemReader<String> firstSimpleReader,
        ItemProcessor<String, String> firstSimpleProcessor, ItemWriter<String> firstSimpleWriter) { //파라미터 명은
        return stepBuilderFactory.get("step1")
            .<String, String>chunk(2)
            .reader(firstSimpleReader)
            .processor(firstSimpleProcessor)
            .writer(firstSimpleWriter)
            .build();
    }
    
    @Primary //spring에서 같은 타입의 메서드 여러개를 Bean 등록시 스프링 컨테이너가 헤깔려하니까, 한놈을 higher priority 부여하는 것
    @Bean
    public Job firstJob(JobBuilderFactory jobBuilderFactory, Step firstStep1, Step secondStep1) { //Step 파라미터명은 메서드 명과 일치시킨다.
        return jobBuilderFactory.get("uppercaseJob") //"uppercaseJob" becomes name of the job (to identify batch job in spring context)
            .incrementer(new RunIdIncrementer()) //unique id incrementor 할 때 쓰면 유용함
            .flow(firstStep1)
            .next(secondStep1) //다른 클래스에 있던 @Bean으로 등록된 Step 가려와서 실행 가능하다.
            .end()
            .build();
    }
}
