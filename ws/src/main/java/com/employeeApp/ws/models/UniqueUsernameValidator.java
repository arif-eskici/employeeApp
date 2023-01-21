package com.employeeApp.ws.models;

import com.employeeApp.ws.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Employee employee = employeeRepository.findByUsername(username);
        if(employee != null) {
            return false;
        }
        return true;
    }
}
