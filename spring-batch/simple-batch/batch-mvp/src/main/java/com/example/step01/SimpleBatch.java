package com.example.step01;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableBatchProcessing
public class SimpleBatch {
    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public SimpleBatch(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    //1. job
    //여기서 이름을 "simple-job"으로 등록하는데, simpleStep()도 등록하는구나
    @Bean
    public Job simpleJob() {
        return jobBuilderFactory
                .get("simple-job")
                .start(simpleStep())
                .build();
    }

    //2. step
    //step에서는 tasklet을 등록하는구나!
    //TODO - 근데 왜 step과 tasklet을 나눠놨지?
    @Bean
    public Step simpleStep() {
        return this.stepBuilderFactory
                .get("simple-step")
                .tasklet(simpleTasklet())
                .build();
    }

    //3. tasklet
    //1초마다 10->9->8->7-> ... 출력하는 배치
    @Bean
    public Tasklet simpleTasklet() {
        return (stepContribution, chunkContext) -> {
            for (int i = 10; i > 0; i--) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
            return RepeatStatus.FINISHED; //return은 이렇게 하는구나
        };
    }

    //4. chunk
    //TODO - tasklet 아니면 chunk쓴다고 하는데, 이 둘의 차이점은 뭐지?
}
