package com.prapps.tutorial.spring.dao.api;

import java.util.List;

import com.prapps.tutorial.spring.entities.Employee;

public interface EmployeeDao {

	void save(Employee employee);
	Employee findEmployeeById(long id);
	List<Employee> findEmployee(String name);
}
