package com.example.step2Job.step2JobParameters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * 전체 금액이 10,000원 이상인 회원들에게 1,000원 캐시백을 주는 배치
 */

/**
 * @JobScope & @StepScope
 *
 *
 * @JobScope와 @StepScope는 Singleton 패턴이 아닌 Annotation이 명시된 메소드의 실행 시점에 Bean이 생성되게 됩니다.
 * 또한 @JobScope와 @StepScope Bean이 생성 될 때 JobParameter가 생성되기 때문에 JobParameter 사용하기 위해선 반드시 Scope를 지정해주어야 합니다.
 * 이는 LateBinding을 하여 JobParameter를 비즈니스 로직 단계에서 할당하여 보다 유연한 설계를 가능하게 하고 서로 다른 Step이 서로를 침범하지 않고 병렬로 실행되게 하기 위함입니다.
 */

@Slf4j
@Configuration
@EnableBatchProcessing
public class JobParameterExampleJobConfig {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//    @Autowired public StepBuilderFactory stepBuilderFactory;
//    @Autowired public EntityManagerFactory entityManagerFactory;
//
//    @Bean
//    public Job ExampleJob() throws Exception {
//
//        Job exampleJob = jobBuilderFactory.get("exampleJob")
//                .start(Step())
//                .build();
//
//        return exampleJob;
//    }
//
//    @Bean
//    @JobScope //TODO - c-2. @JobScope는 Step 선언문에 사용 가능
//    public Step Step() throws Exception {
//        return stepBuilderFactory.get("Step")
//                .<Member,Member>chunk(10)
//                .reader(reader(null))
//                .processor(processor(null))
//                .writer(writer(null))
//                .build();
//    }
//
//    @Bean
//    @StepScope //TODO - c-3. @StepScope는 Step을 구성하는 ItemReader, ItemProcessor, ItemWriter에 사용이 가능
//    public JpaPagingItemReader<Member> reader(@Value("#{jobParameters[date]}")  String date) throws Exception {
//
//        log.info("jobParameters value : " + date);
//
//        Map<String,Object> parameterValues = new HashMap<>();
//        parameterValues.put("amount", 10000);
//
//        return new JpaPagingItemReaderBuilder<Member>()
//                .pageSize(10)
//                .parameterValues(parameterValues)
//                .queryString("SELECT p FROM Member p WHERE p.amount >= :amount ORDER BY id ASC")
//                .entityManagerFactory(entityManagerFactory)
//                .name("JpaPagingItemReader")
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<Member, Member> processor(@Value("#{jobParameters[date]}")  String date){
//
//        return new ItemProcessor<Member, Member>() {
//            @Override
//            public Member process(Member member) throws Exception {
//
//                log.info("jobParameters value : " + date);
//
//                //1000원 추가 적립
//                member.setAmount(member.getAmount() + 1000);
//
//                return member;
//            }
//        };
//    }
//
//    @Bean
//    @StepScope
//    public JpaItemWriter<Member> writer(@Value("#{jobParameters[date]}")  String date){
//
//        log.info("jobParameters value : " + date);
//
//        return new JpaItemWriterBuilder<Member>()
//                .entityManagerFactory(entityManagerFactory)
//                .build();
//    }
}
