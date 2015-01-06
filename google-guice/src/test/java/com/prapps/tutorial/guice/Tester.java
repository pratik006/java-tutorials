package com.prapps.tutorial.guice;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.prapps.tutorial.guice.ioc.EmployeeAppInjector;
import com.prapps.tutorial.guice.service.api.EmployeeService;
import com.prapps.tutorial.guice.vo.Employee;

public class Tester {

	private EmployeeService employeeService; 
	
	@Before public void setUp() {
		Injector injector = Guice.createInjector(new EmployeeAppInjector());        
		employeeService = injector.getInstance(EmployeeService.class);
	}
	
	@Test
	public void testAddEmployee() {
		Employee employee = new Employee();
		employee.setId(101l);
		employee.setName("Souvik Majumder");
		employee.setSalary(1234.0d);
		employeeService.addEmployee(employee);
		
		employee = new Employee();
		employee.setId(102l);
		employee.setName("Pratik Sengupta");
		employee.setSalary(4321.0d);
		employeeService.addEmployee(employee);
		
		employee = new Employee();
		employee.setId(103l);
		employee.setName("Debojit Chatterjee");
		employee.setSalary(8675.0d);
		employeeService.addEmployee(employee);
	}
	
	@Test
	public void testSearchEmployee() {
		testAddEmployee();
		
		Employee employee = employeeService.searchEmployeeById(101l);
		assert("Souvik Majumder".equals(employee.getName()));
		assert(1234d == employee.getSalary());
	}
	
	@Test
	public void testSearchEmployeeByName() {
		testAddEmployee();
		
		Collection<Employee> employees = employeeService.searchEmployeeByName("Pratik Sengupta");
		assert(1 == employees.size());
		
		Employee employee = employees.iterator().next();
		assert("Pratik Sengupta".equals(employee.getName()));
		assert(4321d == employee.getSalary());
	}
}
