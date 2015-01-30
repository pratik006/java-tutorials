package com.prapps.tutorial.spring.service.api;

import java.util.Collection;

import com.prapps.tutorial.spring.vo.Employee;


public interface EmployeeService {

	void addEmployee(Employee employee);
	
	Collection<Employee> searchEmployeeByName(String name);
	
	Employee searchEmployeeById(Long id);
}
