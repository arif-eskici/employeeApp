package com.employeeApp.ws.models;

import jakarta.persistence.*;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    //@Size(min = 11, message = "ID number must be 11 digits")
    private long kimlik_no;

    @NotNull
    @UniqueUsername
    private String username;

    @NotNull
    @Size(min=8, max=255, message = "size must be beetween '8' and '255'")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must have at least 1 uppercase, 1 lowercase letter and 1 number")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

    @ManyToOne
    private Company company;

}

