package com.example.SpringBatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CsvItemWriter implements ItemWriter {
    @Override
    public void write(List items) throws Exception {
        System.out.println("Inside the CSV  Item writer method");
        items.stream().forEach(System.out::println);
    }
}
