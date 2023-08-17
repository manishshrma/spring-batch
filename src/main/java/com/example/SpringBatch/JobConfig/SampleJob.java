package com.example.SpringBatch.JobConfig;

import com.example.SpringBatch.Entity.StudentJdbc;
import com.example.SpringBatch.ThirdTasklet;
import com.example.SpringBatch.processor.FirstItemProcessor;
import com.example.SpringBatch.reader.FirstItemReader;
import com.example.SpringBatch.Entity.Customer;
import com.example.SpringBatch.ThirdTasklet;
import com.example.SpringBatch.processor.FirstItemProcessor;
//import com.example.SpringBatch.reader.CSVItemReader;
import com.example.SpringBatch.reader.FirstItemReader;
import com.example.SpringBatch.reader.JDBCItemReader;
import com.example.SpringBatch.writer.CsvItemWriter;
import com.example.SpringBatch.writer.FirstItemWriter;
import com.example.SpringBatch.writer.MyJdbcItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class SampleJob {
    // For spring batch to undestand, do understand first the structure first. tasklet and chunk based steps are few examples.
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ThirdTasklet thirdTasklet;

    @Autowired
    public FirstItemReader firstItemReader;

    //    @Autowired
//    public CSVItemReader csvItemReader;
    @Autowired
    public FirstItemProcessor firstItemProcessor;
    @Autowired
    public FirstItemWriter firstItemWriter;

    @Autowired
    public CsvItemWriter csvItemWriter;

    @Autowired
    public JDBCItemReader jdbcItemReader;

    @Autowired
    public MyJdbcItemWriter myJdbcItemWriter;

/*    @Bean
    public Job firstJob() throws Exception {
        // You have added the spring batch dependency-
        // so as soon as you start your app, it first look for the method that return the Job.
        // and in our case we created one in JobConfig. thus this method get run.
        // it is same as when you add REST dependency, so any method
        // @ with get,post will get called fist as soon as you hit the request from web
        return jobBuilderFactory.get("first Job")
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .next(secondStep())
                .next(thirdStep())
                .build();
    }
 */

    /*
    method: secondJob()
    desc: it is another job i created, which used the chunk oriented steps.

     */
    @Bean
    public Job secondJob() throws Exception {
      /*   You have added the spring batch dependency-
         so as soon as you start your app, it first look for the method that return the Job.
         and in our case we created one in JobConfig. thus this method get run.
         it is same as when you add REST dependency, so any method
         @ with get,post will get called fist as soon as you hit the request from web
       */
        return jobBuilderFactory.get("second Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .build();
    }


    @Bean
    public Job job_readingCSV() throws Exception {
        return jobBuilderFactory.get("CSV Reading Job")
                .incrementer(new RunIdIncrementer())
                .start(readCSVChunkStep())
                .build();
    }


    public Job databaseJob() throws Exception {
        return jobBuilderFactory.get("db reading job")
                .incrementer(new RunIdIncrementer())
                .start(readDataFromDatabase())
                .build();

    }


    private Step readCSVChunkStep() {

        // coding style- one way
//        csvItemReader.setResource(new FileSystemResource(new File("InputFiles/students.csv")));
//        DefaultLineMapper defaultLineMapper=new DefaultLineMapper<Customer>();
//        DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
//        delimitedLineTokenizer.setNames("ID","First Name","Last Name","Email");
//        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
//        BeanWrapperFieldSetMapper beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<Customer>();
//        beanWrapperFieldSetMapper.setTargetType(Customer.class);
//        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
//        csvItemReader.setLineMapper(new DefaultLineMapper<Customer>());
//        csvItemReader.setLinesToSkip(1);

        // coding style of above code in alternate way
        FlatFileItemReader<Customer> csvItemReader =
                new FlatFileItemReader<Customer>();
        csvItemReader.setResource(new FileSystemResource(
                new File("InputFiles/students.csv")));
        csvItemReader.setLineMapper(new DefaultLineMapper<Customer>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("ID", "First Name", "Last Name", "Email");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {
                    {
                        setTargetType(Customer.class);
                    }
                });
            }
        });
        csvItemReader.setLinesToSkip(1);

        return stepBuilderFactory.get("read csv step")
                .<Customer, Customer>chunk(1)
                .reader(csvItemReader)
//                .processor(csvItemProcessor)
                .writer(csvItemWriter)
                .build();
    }

    private Step readDataFromDatabase() {

        return stepBuilderFactory.get("first chunk of data from db")
                .<StudentJdbc, StudentJdbc>chunk(3)
                .reader(jdbcItemReader.getJdbcItemReader())
//                .processor()
                .writer(myJdbcItemWriter)
                .build();


    }

    /*
    method: firstChunkStep
    desc: this method executed all the chunk oriented steps- reading, processing and writing the data.
    it ake chunk(3) which means it process the data in chunk of size 3 with taking input from datasource  of type Integer and
    output after processing is Long.
     */
    private Step firstChunkStep() {
        return stepBuilderFactory.get("first chunk step")
                .<Integer, Long>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();

    }


    public Step firstStep() {
        return stepBuilderFactory.get("first Step")
                .tasklet(firstTask())
                .build();
    }

    public Step secondStep() throws Exception {
        return stepBuilderFactory.get("second Step")
                .tasklet(secondTask())
                .build();
    }

    private Step thirdStep() {
        return stepBuilderFactory.get("third Step")
                .tasklet(thirdTasklet)   // rather than creating the third task logic in JobConfig
                // keep it seperate
                .build();
    }

    public Tasklet firstTask() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is our first task");
                return RepeatStatus.FINISHED;
            }
        };
    }

    public Tasklet secondTask() throws Exception {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("This is our second task");
//                return RepeatStatus.FINISHED;
//            }
//        };
        /////////// Alternate to use lambda expression ///////////
        Tasklet tasklet = (StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println("This is our second task");
            return RepeatStatus.FINISHED;
        };
//        tasklet.execute(null, null);
        return tasklet;
    }
}
