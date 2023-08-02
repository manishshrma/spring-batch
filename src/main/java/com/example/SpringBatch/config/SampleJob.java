//package com.example.SpringBatch.config;
//
//import com.example.SpringBatch.ThirdTasklet;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.TaskletStepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SampleJob {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public ThirdTasklet thirdTasklet;
//    @Bean
//    public Job firstJob() throws Exception {
//        // You have added the spring batch dependency-
//        // so as soon as you start your app, it first look for the method that return the Job.
//        // and in our case we created one in config. thus this method get run.
//        // it is same as when you add REST dependency, so any method
//        // @ with get,post will get called fist as soon as you hit the request from web
//        return jobBuilderFactory.get("first Job")
//                .start(firstStep())
//                .next(secondStep())
//                .next(thirdStep())
//                .build();
//    }
//
//
//
//    public Step firstStep() {
//        return stepBuilderFactory.get("first Step")
//                .tasklet(firstTask())
//                .build();
//    }
//
//    public Step secondStep() throws Exception {
//        return stepBuilderFactory.get("second Step")
//                .tasklet(secondTask())
//                .build();
//    }
//
//    private Step thirdStep() {
//        return stepBuilderFactory.get("third Step")
//                .tasklet(thirdTasklet)   // rather than creating the third task logic in config
//                // keep it seperate
//                .build();
//    }
//
//    public Tasklet firstTask() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("This is our first task");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//
//    public Tasklet secondTask() throws Exception {
////        return new Tasklet() {
////            @Override
////            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
////                System.out.println("This is our second task");
////                return RepeatStatus.FINISHED;
////            }
////        };
//        /////////// Alternate to use lambda expression ///////////
//        Tasklet tasklet = (StepContribution contribution, ChunkContext chunkContext) -> {
//            System.out.println("This is our second task");
//            return RepeatStatus.FINISHED;
//        };
////        tasklet.execute(null, null);
//        return tasklet;
//    }
//}
