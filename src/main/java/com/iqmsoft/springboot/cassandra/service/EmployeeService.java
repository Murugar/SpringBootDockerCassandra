package com.iqmsoft.springboot.cassandra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.springboot.cassandra.model.Employee;
import com.iqmsoft.springboot.cassandra.repository.EmployeeRepository;
import com.iqmsoft.springboot.cassandra.util.Utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

   @Autowired
   EmployeeRepository employeeRepository;


   public Employee saveEmployee(Employee person) throws ParseException {

       LocalDate now = Utils.formatDate(LocalDate.now());

       System.out.println("saveEmployee LocalDate now : " + now);

       person.setCreatedAt(now);
       return employeeRepository.save(person);
   }

    public Optional<Employee> findEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployee(String id) {
        Optional<Employee> movie = employeeRepository.findById(id);
        if(movie.isPresent()) {
            Employee deletedPerson = movie.get();
            employeeRepository.delete(deletedPerson);
        }
    }

    public List<Employee> allEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public void updateEmployee(String id,Employee employeeForUpdate) {

        employeeRepository.findById(id).ifPresentOrElse(employee -> {
                    employee.setName(employeeForUpdate.getName());
                    employee.setSurname(employeeForUpdate.getSurname());
                    employee.setUsername(employeeForUpdate.getUsername());
                    employee.setBirthDate(employeeForUpdate.getBirthDate());
                    employee.setEmail(employeeForUpdate.getEmail());
                    employee.setPassword(employeeForUpdate.getPassword());
                    employee.setActive(employeeForUpdate.getActive());
                    employee.setCreatedAt(employeeForUpdate.getCreatedAt());
                    employeeRepository.save(employee);

                }, () -> {
                    throw new RuntimeException("No Record With this Id!");
                }
        );

    }
}
