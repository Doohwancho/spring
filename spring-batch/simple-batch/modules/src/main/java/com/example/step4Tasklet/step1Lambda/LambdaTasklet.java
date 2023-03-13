package com.example.step4Tasklet.step1Lambda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableBatchProcessing
@Configuration
public class LambdaTasklet {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job TaskletJob(){

        Job customJob = jobBuilderFactory.get("taskletJob")
                .start(TaskStep())
                .build();

        return customJob;
    }

    @Bean
    public Step TaskStep(){
        return stepBuilderFactory.get("taskletStep")
                .tasklet((contribution, chunkContext) ->{

                    //비즈니스 로직
                    for(int idx = 0; idx < 10; idx ++){
                        log.info("[idx] = " + idx);
                    }

                    return RepeatStatus.FINISHED;
                }).build();
    }
}
