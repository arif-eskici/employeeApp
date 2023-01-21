package com.employeeApp.ws.controllers;

import com.employeeApp.ws.exception.ApiError;
import com.employeeApp.ws.models.Employee;
import com.employeeApp.ws.services.EmployeeService;
import com.employeeApp.ws.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public GenericResponse createEmployee(@Valid @RequestBody Employee employee) {
        employeeService.save(employee);
        return new GenericResponse("employee created");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exception) {
        ApiError error = new ApiError(400, "validation error", "/employee");
        Map<String, String> validationErrors = new HashMap<>();
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.setValidationErrors(validationErrors);
        return error;
    }

    @GetMapping("/employee")
    Page<Employee> getEmployees(Pageable page) {
        return employeeService.getEmployees(page);
    }

    @GetMapping("/employee/{id}")
    Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employee/{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.updateEmployee(newEmployee, id);
    }

    @DeleteMapping("/employee/{id}")
    GenericResponse deleteUser(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new GenericResponse("Employee with id " + id + " has been deleted success.");
    }

    @DeleteMapping("/employees")
    GenericResponse del(){
        employeeService.deleteAllEmployees();
        return new GenericResponse("all of the employees removed");
    }
}
