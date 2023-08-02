package com.example.SpringBatch.dao;

import com.example.SpringBatch.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
    @Autowired
    private EmployeeRepo employeeRepo;

    public void saveEmployee(Employee employee) {
        Object obj=employeeRepo.save(employee);

    }

}
