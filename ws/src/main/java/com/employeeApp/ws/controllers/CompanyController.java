package com.employeeApp.ws.controllers;

import com.employeeApp.ws.models.Company;
import com.employeeApp.ws.services.CompanyService;
import com.employeeApp.ws.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/company")
    GenericResponse saveCompany(@RequestBody Company company) {
        companyService.save(company);
        return new GenericResponse("Company is saved");
    }

    @GetMapping("/companies")
    Page<Company> getCompanies(Pageable page) {
        return companyService.getCompanies(page);
    }

    @GetMapping("company/{id}")
    Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/company/{id}")
    Company updateCompany(@RequestBody Company newCompany, @PathVariable Long id) {
        return companyService.updateCompany(newCompany, id);
    }

    @DeleteMapping("/company/{id}")
    GenericResponse deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new GenericResponse("Company with id" + id + " removed");
    }

    @DeleteMapping("/companies")
    GenericResponse deleteAllCompanies() {
        companyService.deleteAllCompanies();
        return  new GenericResponse("All of the companies removed.");
    }

}
