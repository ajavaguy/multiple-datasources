package com.example.multipledatasources.datasources.employee;

import com.example.multipledatasources.model.employee.Employee;
import com.example.multipledatasources.model.employee.EmployeeRepository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDataSource implements EmployeeRepository {
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeDataSource(@Qualifier("multipleDatasourcesSQLSession") SqlSessionTemplate sqlSessionTemplate) {
        this.employeeMapper = sqlSessionTemplate.getMapper(EmployeeMapper.class);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.getAllEmployees();
    }
}
