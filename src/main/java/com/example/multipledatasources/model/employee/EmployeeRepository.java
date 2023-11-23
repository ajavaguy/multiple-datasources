package com.example.multipledatasources.model.employee;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {
    List<Employee> getAllEmployees();
}
