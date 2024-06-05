package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@EnableBatchProcessing
@SpringBootApplication
public class Application{
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job firstJob; // SimpleBatch에 firstJob 메서드 명과 이름이 일치해야 한다.
    
    @Autowired
    private Job secondJob; // SecondSimpleBatch에 secondJob 메서드 명과 이름이 일치해야 한다.
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            try {
                //여러 배치 잡들을 실행하기
                jobLauncher.run(firstJob, new JobParameters()); // Launch first job
//                jobLauncher.run(secondJob, new JobParameters()); // Launch second job
            } catch (JobExecutionException e) {
                e.printStackTrace();
            }
        };
    }
}