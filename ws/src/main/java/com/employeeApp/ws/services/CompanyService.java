package com.employeeApp.ws.services;

import com.employeeApp.ws.exception.NotFoundException;
import com.employeeApp.ws.models.Company;
import com.employeeApp.ws.repositories.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void save(Company company) {
        companyRepository.save(company);
    }

    public Page<Company> getCompanies(Pageable page) {
        return companyRepository.findAll(page);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Company updateCompany(Company newCompany, Long id) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.setName(newCompany.getName());
                    company.setManager(newCompany.getManager());
                    company.setAddress(newCompany.getAddress());
                    company.setDepartment(newCompany.getDepartment());
                    return companyRepository.save(company);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteCompany(Long id) {
        if(!companyRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        companyRepository.deleteById(id);
    }

    public void deleteAllCompanies() {
        companyRepository.deleteAll();

    }
}
