package com.prapps.tutorial.guice.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.prapps.tutorial.guice.dao.api.EmployeeDao;
import com.prapps.tutorial.guice.service.api.EmployeeService;
import com.prapps.tutorial.guice.vo.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	//private Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();
	@Inject private EmployeeDao employeeDao;
	
	@Transactional
	public void addEmployee(Employee employee) {
		/*employeeMap.put(employee.getId(), employee);*/
		com.prapps.tutorial.guice.entities.Employee emp = new com.prapps.tutorial.guice.entities.Employee(
				employee.getId(),
				employee.getName(),
				employee.getSalary()
				);
		employeeDao.save(emp);
	}

	public Collection<Employee> searchEmployeeByName(String name) {
		Collection<Employee> result = new ArrayList<Employee>();
		/*for(Entry<Long, Employee> entry : employeeMap.entrySet()) {
			if(entry.getValue().getName().equals(name)) {
				result.add(entry.getValue());
			}
		}*/
		List<com.prapps.tutorial.guice.entities.Employee> employees = employeeDao.findEmployee(name);
		for(com.prapps.tutorial.guice.entities.Employee emp : employees) {
			result.add(new Employee(emp.getId(), emp.getName(), emp.getSalary()));
		}
		return result;
	}

	public Employee searchEmployeeById(Long id) {
		//return employeeMap.get(id);
		com.prapps.tutorial.guice.entities.Employee employee = employeeDao.findEmployeeById(id);
		Employee empvo = new Employee();
		empvo.setId(employee.getId());
		empvo.setName(employee.getName());
		empvo.setSalary(employee.getSalary());
		return empvo;
	}

}
