package com.example.multipledatasources.controller.employee;

import com.example.multipledatasources.model.address.Address;
import com.example.multipledatasources.model.employee.Employee;
import com.example.multipledatasources.service.address.AddressService;
import com.example.multipledatasources.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private AddressService addressService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value="/{employeeId}", method = RequestMethod.GET)
    public Employee getEmployeeBy(@PathVariable("employeeId") Integer employeeId) {
        Address address = addressService.findBy(employeeId);
        return new Employee(null, null, address);
    }
}
