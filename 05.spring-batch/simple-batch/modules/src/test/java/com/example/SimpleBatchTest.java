package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBatchTest
@SpringBootTest
public class SimpleBatchTest {
    
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    
    @Autowired
    private Job firstJob;
    
    @Test
    public void testFirstJob() throws Exception {
        // Set the job to be launched
        jobLauncherTestUtils.setJob(firstJob);
    
        // Launch the job
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    
        // Assert job execution status
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }
}
