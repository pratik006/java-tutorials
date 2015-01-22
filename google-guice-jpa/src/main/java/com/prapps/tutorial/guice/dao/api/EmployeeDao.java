package com.prapps.tutorial.guice.dao.api;

import java.util.List;

import com.prapps.tutorial.guice.entities.Employee;

public interface EmployeeDao {

	void save(Employee employee);
	Employee findEmployeeById(long id);
	List<Employee> findEmployee(String name);
}
