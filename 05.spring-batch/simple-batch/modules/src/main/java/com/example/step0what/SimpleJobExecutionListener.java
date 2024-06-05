package com.example.step0what;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class SimpleJobExecutionListener implements JobExecutionListener {
    
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job started: " + jobExecution.getJobInstance().getJobName());
    }
    
    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Job ended: " + jobExecution.getJobInstance().getJobName() + " with status: " + jobExecution.getStatus());
    }
}
