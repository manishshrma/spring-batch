package com.example.SpringBatch.dao;

import com.example.SpringBatch.Entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepo extends CrudRepository<Employee,String> {

}
