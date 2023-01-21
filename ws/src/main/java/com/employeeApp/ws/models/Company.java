package com.employeeApp.ws.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String manager;

    private String address;

    private String department;

    @OneToMany
    private List<Employee> employees;

}
