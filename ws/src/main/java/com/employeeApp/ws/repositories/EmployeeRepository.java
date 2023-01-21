package com.employeeApp.ws.repositories;

import com.employeeApp.ws.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAll();
    Employee findByUsername(String username);
}
