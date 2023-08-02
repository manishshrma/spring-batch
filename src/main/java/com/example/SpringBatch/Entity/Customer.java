package com.example.SpringBatch.Entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
