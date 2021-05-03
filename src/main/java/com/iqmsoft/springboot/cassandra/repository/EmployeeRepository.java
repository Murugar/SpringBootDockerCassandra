package com.iqmsoft.springboot.cassandra.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.iqmsoft.springboot.cassandra.model.Employee;

@Repository
public interface EmployeeRepository extends CassandraRepository<Employee, String> {

}
