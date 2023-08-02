package com.example.SpringBatch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


/*
method: process(I item)
args: item here- get the value from the output of read method.
desc: take output or read data from the readd method, and process it, and return the processed data.
 */

@Component
public class FirstItemProcessor implements ItemProcessor<Integer, Long> {

    @Override
    public Long process(Integer item) throws Exception {
        System.out.println("Inside the item processor");
        item = item + 20;
        return Long.valueOf(item);


    }
}
