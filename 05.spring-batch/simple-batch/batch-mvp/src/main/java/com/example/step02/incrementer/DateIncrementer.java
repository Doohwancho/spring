package com.example.step02.incrementer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.time.LocalDateTime;

public class DateIncrementer implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(final JobParameters parameters) {
        return new JobParametersBuilder(parameters)
                .addString("currentDate", LocalDateTime.now().toString()) //현재 시간 추가해주는 애구나
                .toJobParameters();
    }
}
