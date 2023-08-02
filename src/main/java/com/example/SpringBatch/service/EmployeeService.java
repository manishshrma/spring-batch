package com.example.SpringBatch.service;

import com.example.SpringBatch.Entity.Employee;
import com.example.SpringBatch.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    public boolean testconnection(Employee employee)
    {
        employeeDao.saveEmployee(employee);
        return true;


    }
}
