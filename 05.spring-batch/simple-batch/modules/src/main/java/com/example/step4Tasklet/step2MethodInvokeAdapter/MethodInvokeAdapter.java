package com.example.step4Tasklet.step2MethodInvokeAdapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class MethodInvokeAdapter {
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//    @Autowired public StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job TaskletJob(){
//
//        Job customJob = jobBuilderFactory.get("taskletJob")
//                .start(TaskStep())
//                .build();
//
//        return customJob;
//    }
//
//    @Bean
//    public Step TaskStep(){
//        return stepBuilderFactory.get("taskletStep")
//                .tasklet(myTasklet()).build();
//    }
//
//    @Bean
//    public CustomService service() {
//        return new CustomService ();
//    }
//
//    @Bean
//    public MethodInvokingTaskletAdapter myTasklet() {
//        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
//
//        adapter.setTargetObject(service());
//        adapter.setTargetMethod("businessLogic");
//
//        return adapter;
//    }
}
