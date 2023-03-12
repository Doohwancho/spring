package com.example.step02;

import com.example.step02.dto.CsvTestDto;
import com.example.step02.incrementer.DateIncrementer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class SimpleBatch {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public SimpleBatch(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }


    @Bean
    public Job simpleJob() {
        return jobBuilderFactory
                .get("simple-job")
                .start(simpleStep())
                .incrementer(new DateIncrementer()) //TODO - what is incrementer?
                .build();
    }

    @Bean
    public Step simpleStep() {
        return this.stepBuilderFactory
                .get("simple-step")
                .<CsvTestDto, CsvTestDto>chunk(100) //한번에 100개씩 처리하는구나. DTO도 쓰네.
                .reader(fileItemReader())//아 읽는애 따로 쓰는애 따로구나
                .writer(itemWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<CsvTestDto> fileItemReader() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(",");
        lineTokenizer.setNames("iYear", "industryAggregation", "industryCode", "industryName", "units",
                "variableCode",
                "variableName", "variableCategory", "iValue", "industryCode2");

        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(CsvTestDto.class);
        fieldSetMapper.setStrict(false);

        DefaultLineMapper defaultLineMapper = new DefaultLineMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return new FlatFileItemReaderBuilder()
                .name("fileItemReader")
                .resource(new ClassPathResource("big-data.csv"))
                .lineMapper(defaultLineMapper) //line mapper 등록
                .linesToSkip(1) //첫번쨰 라인은 header이기 때문에 skip
                .build();
    }


    @Bean
    public ItemWriter<CsvTestDto> itemWriter() {
        return items -> {
            for (CsvTestDto item : items) {
                System.out.println(item);
            }
        };
    }

}