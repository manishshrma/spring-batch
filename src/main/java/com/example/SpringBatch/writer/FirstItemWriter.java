package com.example.SpringBatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<Long> {
/*
method: writeItems- It take all the values that has been processed and write at one shot all of them.
 */

    @Override
    public void write(List<? extends Long> items) throws Exception {
        System.out.println("Inside the item writer method");
        items.stream().forEach(System.out::println);
    }
}
