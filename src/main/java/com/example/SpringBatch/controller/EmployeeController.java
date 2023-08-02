package com.example.SpringBatch.controller;

import com.example.SpringBatch.Entity.Employee;
import com.example.SpringBatch.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/testdb")
    public boolean checkDatabaseConnection(@RequestBody Employee employee) {
       return employeeService.testconnection(employee);

    }

}
