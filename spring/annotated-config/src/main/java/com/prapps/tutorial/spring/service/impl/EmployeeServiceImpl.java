package com.prapps.tutorial.spring.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.prapps.tutorial.spring.dao.api.EmployeeDao;
import com.prapps.tutorial.spring.service.api.EmployeeService;
import com.prapps.tutorial.spring.vo.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	@Inject private EmployeeDao employeeDao;
	
	//@Transactional
	public void addEmployee(Employee employee) {
		com.prapps.tutorial.spring.entities.Employee emp = new com.prapps.tutorial.spring.entities.Employee(
				employee.getId(),
				employee.getName(),
				employee.getSalary()
				);
		employeeDao.save(emp);
	}

	public Collection<Employee> searchEmployeeByName(String name) {
		Collection<Employee> result = new ArrayList<Employee>();
		List<com.prapps.tutorial.spring.entities.Employee> employees = employeeDao.findEmployee(name);
		for(com.prapps.tutorial.spring.entities.Employee emp : employees) {
			result.add(new Employee(emp.getId(), emp.getName(), emp.getSalary()));
		}
		return result;
	}

	public Employee searchEmployeeById(Long id) {
		com.prapps.tutorial.spring.entities.Employee employee = employeeDao.findEmployeeById(id);
		Employee empvo = new Employee();
		empvo.setId(employee.getId());
		empvo.setName(employee.getName());
		empvo.setSalary(employee.getSalary());
		return empvo;
	}

}
