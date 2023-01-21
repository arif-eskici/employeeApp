package com.employeeApp.ws.repositories;

import com.employeeApp.ws.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
