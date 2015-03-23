package com.prapps.tutorial.webservice.jaxws.impl;

import javax.jws.WebService;

import com.prapps.tutorial.webservice.jaxws.api.EmployeeService;
import com.prapps.tutorial.webservice.jaxws.vo.Employee;

@WebService(endpointInterface = "com.prapps.tutorial.webservice.jaxws.api.EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public Employee findPerson(long id) {
		Employee emp = new Employee();
		emp.setName("Test123");
		emp.setAge(35);
		emp.setId(id);
		return emp;
	}
}
