package com.iqmsoft.springboot.cassandra.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.iqmsoft.springboot.cassandra.error.ResourceNotFoundException;
import com.iqmsoft.springboot.cassandra.model.Employee;
import com.iqmsoft.springboot.cassandra.service.EmployeeService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        LOG.info("Getting all employees");
        return employeeService.allEmployees();
    }

    @PostMapping("/employees/save")
    public Employee addEmployee(@RequestBody Employee employee) throws ParseException {
        LOG.info("Saving Employee");
        employeeService.saveEmployee(employee);
        LOG.info("Saved Employee : " + employee);
        return employee;
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable("id") String employeeId){
        LOG.info("Finding By Employee Id");
        Employee employee = employeeService.findEmployeeById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found... by Id : " +employeeId));
        LOG.info("Found By Employee Id : " + employee);
        return employee;
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value="id") String employeeId, @RequestBody Employee employeeDetatils){
        LOG.info("Updating By Employee Id");
        employeeService.updateEmployee(employeeId,employeeDetatils);
        LOG.info("Updated By Employee Id : " + employeeDetatils);
        return employeeDetatils;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable(value="id") String employeeId) {
        LOG.info("Deleting By Employee Id");
        employeeService.deleteEmployee(employeeId);
        LOG.info("Deleted By Employee Id : " + employeeId);
    }

}
