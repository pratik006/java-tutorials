package com.prapps.tutorial.guice.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.prapps.tutorial.guice.service.api.EmployeeService;
import com.prapps.tutorial.guice.vo.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	private Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();
	
	public void addEmployee(Employee employee) {
		employeeMap.put(employee.getId(), employee);
	}

	public Collection<Employee> searchEmployeeByName(String name) {
		Collection<Employee> result = new ArrayList<Employee>();
		for(Entry<Long, Employee> entry : employeeMap.entrySet()) {
			if(entry.getValue().getName().equals(name)) {
				result.add(entry.getValue());
			}
		}
		return result;
	}

	public Employee searchEmployeeById(Long id) {
		return employeeMap.get(id);
	}

}
