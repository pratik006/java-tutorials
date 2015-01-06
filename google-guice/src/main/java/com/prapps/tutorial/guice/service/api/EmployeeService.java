package com.prapps.tutorial.guice.service.api;

import java.util.Collection;

import com.prapps.tutorial.guice.vo.Employee;

public interface EmployeeService {

	void addEmployee(Employee employee);
	
	Collection<Employee> searchEmployeeByName(String name);
	
	Employee searchEmployeeById(Long id);
}
