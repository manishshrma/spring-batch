package com.example.SpringBatch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class FirstItemReader implements ItemReader {

    /*
    method : read()
    desc: this method take data from source( here is the arrayList)
    one by one and read it. And Give the read data to the item processor if provided.

     */

    List<Integer> dataSource = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    int i = 0;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Inside Item Reader");
        Integer item;
        if (i < dataSource.size()) {
            item = dataSource.get(i);
            i++;
            return item;
        }
        i = 0;
        return null;
    }
}
