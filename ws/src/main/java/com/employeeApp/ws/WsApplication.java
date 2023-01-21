package com.employeeApp.ws;

import com.employeeApp.ws.models.Company;
import com.employeeApp.ws.models.Employee;
import com.employeeApp.ws.repositories.EmployeeRepository;
import com.employeeApp.ws.services.CompanyService;
import com.employeeApp.ws.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

	public static void main(String[] args) {

		SpringApplication.run(WsApplication.class, args);
	}

	@Bean
	CommandLineRunner createEmployees(EmployeeService employeeService, CompanyService companyService, EmployeeRepository employeeRepository) {
		return (args) -> {
			for (int i = 1; i <= 5; i++) {
				Employee employee = new Employee();
				employee.setKimlik_no(1111111111);
				employee.setUsername("employee" + i);
				employee.setPassword("P4ssword");
				employee.setEmail("demo" + i + "@gmail.com");
				employeeService.save(employee);
			}

				List<Employee> employees = employeeRepository.findAll();
				Company company = new Company();
				company.setName("güven_bilişim");
				company.setManager("arif");
				company.setAddress("Izmir");
				company.setDepartment("yazılım-danışmanlık");
				company.setEmployees(employees);
				companyService.save(company);

		};
	}
}




