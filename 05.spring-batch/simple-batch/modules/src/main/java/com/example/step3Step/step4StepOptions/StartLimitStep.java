package com.example.step3Step.step4StepOptions;

import com.example.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class StartLimitStep {
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//    @Autowired public StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job ExampleJob(){
//
//        Job exampleJob = jobBuilderFactory.get("exampleJob")
//                .start(Step())
//                .build();
//
//        return exampleJob;
//    }
//
//    @Bean
//    @JobScope
//    public Step Step() {
//        return stepBuilderFactory.get("step")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("Step!");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();


//        //step1) startLimit
//        return stepBuilderFactory.get("Step")
//                .startLimit(3) //해당 Step의 실패 이후 재시작 가능 횟수를 의미합니다. startlimit 이후 실행에서는 Exception이 발생하게 됩니다.
//                .<Member,Member>chunk(10)
//                .reader(reader(null))
//                .processor(processor(null))
//                .writer(writer(null))
//                .build();
//
//        //step2) skipLimit
//        return stepBuilderFactory.get("Step")
//                .<Member,Member>chunk(10)
//                .reader(reader(null))
//                .processor(processor(null))
//                .writer(writer(null))
//                .faultTolerant()
//                .skipLimit(1) // skip 허용 횟수, 해당 횟수 초과시 Error 발생, Skip 사용시 필수 설정
//                .skip(NullPointerException.class)// NullPointerException에 대해선 Skip
//                .noSkip(SQLException.class) // SQLException에 대해선 noSkip
//                //.skipPolicy(new CustomSkipPolilcy) // 사용자가 커스텀하며 Skip Policy 설정 가능
//                .build();
//
//        //step3) retryLimit
//        return stepBuilderFactory.get("Step")
//                .<Member,Member>chunk(10)
//                .reader(reader(null))
//                .processor(processor(null))
//                .writer(writer(null))
//                .faultTolerant()
//                .retryLimit(1) //retry 횟수, retry 사용시 필수 설정, 해당 Retry 이후 Exception시 Fail 처리
//                .retry(SQLException.class) // SQLException에 대해선 Retry 수행
//                .noRetry(NullPointerException.class) // NullPointerException에 no Retry
//                //.retryPolicy(new CustomRetryPolilcy) // 사용자가 커스텀하며 Retry Policy 설정 가능
//                .build();
//
//        //step4) noRollback
//        return stepBuilderFactory.get("Step")
//                .<Member,Member>chunk(10)
//                .reader(reader(null))
//                .processor(processor(null))
//                .writer(writer(null))
//                .faultTolerant()
//                .noRollback(NullPointerException.class) // NullPointerException 발생  rollback이 되지 않게 설정
//                .build();
//    }
}
