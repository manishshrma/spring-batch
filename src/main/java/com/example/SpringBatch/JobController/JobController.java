package com.example.SpringBatch.JobController;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    JobLauncher jobLauncher; // using this obj, we can launch job manually

    @Autowired
    @Qualifier("job_readingCSV")
    Job job_readingCSV;

    @Autowired
    @Qualifier("secondJob")
    Job SecondJob;

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> jobParametersMap = new HashMap<>();
        jobParametersMap.put("CurrentTime", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(jobParametersMap);

        if (jobName.equalsIgnoreCase("job_readingCSV")) {
            jobLauncher.run(job_readingCSV, jobParameters);
            return "csv reading started";
        } else {
            jobLauncher.run(SecondJob, jobParameters);
            return "second job started";
        }
    }
}
