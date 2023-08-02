package com.example.SpringBatch.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private String id;
    private String name;
    private int age;
    private String marks;
}
