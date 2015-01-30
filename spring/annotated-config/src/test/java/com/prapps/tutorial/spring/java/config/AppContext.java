package com.prapps.tutorial.spring.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.prapps.tutorial.spring.dao.api.EmployeeDao;
import com.prapps.tutorial.spring.dao.impl.EmployeeDaoImpl;
import com.prapps.tutorial.spring.service.api.EmployeeService;
import com.prapps.tutorial.spring.service.impl.EmployeeServiceImpl;

@Configuration
@ComponentScan("com.prapps.tutorial")
public class AppContext {

	@Bean
	public EmployeeService employeeService() {
		EmployeeService employeeService = new EmployeeServiceImpl();
		return employeeService;
	}

	@Bean
	public EmployeeDao employeeDao() {
		EmployeeDao employeeDao = new EmployeeDaoImpl();

		return employeeDao;
	}

}
