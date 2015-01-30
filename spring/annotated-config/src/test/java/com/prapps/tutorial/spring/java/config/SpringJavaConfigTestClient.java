package com.prapps.tutorial.spring.java.config;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prapps.tutorial.spring.ioc.AppContext;
import com.prapps.tutorial.spring.service.api.EmployeeService;
import com.prapps.tutorial.spring.vo.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppContext.class})
public class SpringJavaConfigTestClient {

	private static final Logger LOG = Logger.getLogger(SpringJavaConfigTestClient.class);
	@Inject private EmployeeService employeeService; 
	
	@Test
	@Transactional
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
	@Transactional
	public void testSearchEmployee() {
		testAddEmployee();
		
		Employee employee = employeeService.searchEmployeeById(101l);
		assert("Souvik Majumder".equals(employee.getName()));
		assert(1234d == employee.getSalary());
	}
	
	@Test
	@Transactional
	public void testSearchEmployeeByName() {
		testAddEmployee();
		
		Collection<Employee> employees = employeeService.searchEmployeeByName("Pratik Sengupta");
		assert(employees.size() > 0);
		
		Employee employee = employees.iterator().next();
		assert("Pratik Sengupta".equals(employee.getName()));
		assert(4321d == employee.getSalary());
		LOG.debug(employee);
	}
}
