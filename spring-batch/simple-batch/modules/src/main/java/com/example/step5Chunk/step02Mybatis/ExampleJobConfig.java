package com.example.step5Chunk.step02Mybatis;

import com.example.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 전체 금액이 10,000원 이상인 회원들에게 1,000원 캐시백을 주는 배치
 */

/**
 * common error
 *
 * Batch execution returned invalid results. Expected 1 but number of BatchResult objects returned was 2
 *
 * MyBatisBatchItemWriter Class에 write에서는 result Size 1이 아닌경우 Exception을 Throws 합니다.
 * 저는 Processor 안에서 배치 처리를 하며 write와 별개로 DB Insert를 수행하고 있었는데 해당 Insert로 인해서 result Size가 2가 되면서 다음과 같은 Error가 발생 했었습니다.
 * 해당 문제 해결 방법은 MyBatisBatchItemWriterBuilder에서 assertUpdates(false)로 간단하게 해결 할 수 있습니다.
 */

@Slf4j
@Configuration
@EnableBatchProcessing
public class ExampleJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;
    @Autowired public SqlSessionFactory sqlSessionFactory;

    @Bean
    public Job ExampleJob() throws Exception {

        Job exampleJob = jobBuilderFactory.get("exampleJob")
                .start(Step())
                .build();

        return exampleJob;
    }

    @Bean
    @JobScope
    public Step Step() throws Exception {
        return stepBuilderFactory.get("Step")
                .<Member,Member>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public MyBatisPagingItemReader<Member> reader() throws Exception {

        Map<String,Object> parameterValues = new HashMap<>();
        parameterValues.put("amount", "10000");

        return new MyBatisPagingItemReaderBuilder<Member>()
                .pageSize(10)
                .sqlSessionFactory(sqlSessionFactory)
                //Mapper안에서도 Paging 처리 시 OrderBy는 필수!
                .queryId("com.example.batch.db.mapper.memberMapper.selectMemberInfo") //여기에 sql문을 따로 정의해줘야 한다.
                .parameterValues(parameterValues)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Member, Member> processor(){

        return new ItemProcessor<Member, Member>() {
            @Override
            public Member process(Member member) throws Exception {

                //1000원 추가 적립
                member.setAmount(member.getAmount() + 1000);

                return member;
            }
        };
    }

    @Bean
    @StepScope
    public MyBatisBatchItemWriter<Member> writer(){
        return new MyBatisBatchItemWriterBuilder<Member>()
                //.assertUpdates(false)
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.finnq.batch.db.mapper.memberMapper.insertMember")
                .build();
    }

}
