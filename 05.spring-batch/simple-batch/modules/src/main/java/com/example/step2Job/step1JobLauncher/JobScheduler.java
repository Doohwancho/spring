package com.example.step2Job.step1JobLauncher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class JobScheduler {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job ExampleJob;
//
//    //runs every 1 minutes
//    @Scheduled(cron = "1 * * * * *") //TODO - c-1. @Scheduled - specify time interval or cros expression at which the task should be executed.
//    public void jobSchduled() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
//            JobRestartException, JobInstanceAlreadyCompleteException {
//
//        Map<String, JobParameter> jobParametersMap = new HashMap<>();
//
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//        Date time = new Date();
//
//        String time1 = format1.format(time);
//
//        jobParametersMap.put("date",new JobParameter(time1));
//
//        JobParameters parameters = new JobParameters(jobParametersMap);
//
//        JobExecution jobExecution = jobLauncher.run(ExampleJob, parameters);
//
//        while (jobExecution.isRunning()) {
//            log.info("...");
//        }
//
//        log.info("Job Execution: " + jobExecution.getStatus());
//        log.info("Job getJobConfigurationName: " + jobExecution.getJobConfigurationName());
//        log.info("Job getJobId: " + jobExecution.getJobId());
//        log.info("Job getExitStatus: " + jobExecution.getExitStatus());
//        log.info("Job getJobInstance: " + jobExecution.getJobInstance());
//        log.info("Job getStepExecutions: " + jobExecution.getStepExecutions());
//        log.info("Job getLastUpdated: " + jobExecution.getLastUpdated());
//        log.info("Job getFailureExceptions: " + jobExecution.getFailureExceptions());
//
//    }
}