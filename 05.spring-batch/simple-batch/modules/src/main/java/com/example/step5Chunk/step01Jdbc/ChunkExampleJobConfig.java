package com.example.step5Chunk.step01Jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.context.annotation.Configuration;


/**
 * 전체 금액이 10,000원 이상인 회원들에게 1,000원 캐시백을 주는 배치
 */

@Slf4j
@Configuration
@EnableBatchProcessing
public class ChunkExampleJobConfig {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//    @Autowired public StepBuilderFactory stepBuilderFactory;
//    @Autowired public DataSource dataSource;
//
//    @Bean
//    public Job ExampleJob() throws Exception {
//
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
//    public Step Step() throws Exception {
//        return stepBuilderFactory.get("Step")
//                .<Member, Member>chunk(10)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcPagingItemReader<Member> reader() throws Exception {
//
//        Map<String,Object> parameterValues = new HashMap<>();
//        parameterValues.put("amount", "10000");
//
//        //pageSize와 fethSize는 동일하게 설정
//        return new JdbcPagingItemReaderBuilder<Member>()
//                .pageSize(10) //대용량 시에는 pageSize와 fetchSize를 늘려야함ƒ
//                .fetchSize(10)
//                .dataSource(dataSource)
//                .rowMapper(new BeanPropertyRowMapper<>(Member.class))
//                .queryProvider(customQueryProvider()) //jdbc에 맞는 query를 여기에 넣는구나
//                .parameterValues(parameterValues)
//                .name("JdbcPagingItemReader")
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<Member, Member> processor(){
//
//        return new ItemProcessor<Member, Member>() {
//            @Override
//            public Member process(Member member) throws Exception {
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
//    public JdbcBatchItemWriter<Member> writer(){
//        return new JdbcBatchItemWriterBuilder<Member>()
//                .dataSource(dataSource)
//                .sql("UPDATE MEMBER SET AMOUNT = :amount WHERE ID = :id")
//                .beanMapped()
//                .build();
//    }
//
//    public PagingQueryProvider customQueryProvider() throws Exception {
//        SqlPagingQueryProviderFactoryBean queryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
//
//        queryProviderFactoryBean.setDataSource(dataSource);
//
//        queryProviderFactoryBean.setSelectClause("SELECT ID, NAME, EMAIL, NICK_NAME, STATUS, AMOUNT ");
//        queryProviderFactoryBean.setFromClause("FROM MEMBER ");
//        queryProviderFactoryBean.setWhereClause("WHERE AMOUNT >= :amount");
//
//        Map<String,Order> sortKey = new HashMap<>();
//        sortKey.put("id", Order.ASCENDING);
//
//        queryProviderFactoryBean.setSortKeys(sortKey);
//
//        return queryProviderFactoryBean.getObject();
//
//    }

}