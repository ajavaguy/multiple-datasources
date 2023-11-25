package com.example.multipledatasources.datasources.employee;

import com.example.multipledatasources.model.employee.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> getAllEmployees();
}
