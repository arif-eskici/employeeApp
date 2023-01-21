package com.employeeApp.ws.exception;

public class NotFoundException  extends RuntimeException{

    public NotFoundException(Long id) {
        super("Could not found the employee/company with id " + id);
    }
}
