package com.example.SpringBatch.jobListener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before Job: "+jobExecution.getJobInstance().getJobName());
        System.out.println("Before Job: "+jobExecution.getJobParameters());
        System.out.println("Before Job: "+jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("test","test");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After Job: "+jobExecution.getJobInstance().getJobName());
        System.out.println("After Job: "+jobExecution.getJobParameters());
        System.out.println("After Job: "+jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("test","test");
    }
}
