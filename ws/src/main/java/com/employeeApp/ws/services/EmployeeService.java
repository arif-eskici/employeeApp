package com.employeeApp.ws.services;

import com.employeeApp.ws.exception.NotFoundException;
import com.employeeApp.ws.models.Employee;
import com.employeeApp.ws.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void save(Employee employee) {
        String encryptedPassword = this.passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encryptedPassword);
        employeeRepository.save(employee);
    }

    public Page<Employee> getEmployees(Pageable page) {
        return employeeRepository.findAll(page);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Employee updateEmployee(Employee newEmployee, Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setEmail(newEmployee.getEmail());
                    employee.setUsername(newEmployee.getUsername());
                    employee.setPassword(newEmployee.getPassword());
                    employee.setKimlik_no(newEmployee.getKimlik_no());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteEmployee(Long id) {
        if(!employeeRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }
}
