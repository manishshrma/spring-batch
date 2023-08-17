package com.example.SpringBatch.writer;

import com.example.SpringBatch.Entity.StudentJdbc;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyJdbcItemWriter implements ItemWriter<StudentJdbc> {

    @Override
    public void write(List<? extends StudentJdbc> items) throws Exception {
        System.out.println("Inside the Item Writer");

        System.out.println(items.stream().map((student) -> student.getFirstName()).collect(Collectors.toList()));

    }
}
