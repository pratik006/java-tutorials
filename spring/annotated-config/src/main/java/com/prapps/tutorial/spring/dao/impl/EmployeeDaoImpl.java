package com.prapps.tutorial.spring.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.prapps.tutorial.spring.dao.api.EmployeeDao;
import com.prapps.tutorial.spring.entities.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	//@Inject -- should not use @Inject
	@PersistenceContext private EntityManager emp;
	
	public Employee findEmployeeById(long id) {
		return emp.find(Employee.class, id);
	}
	
	public List<Employee> findEmployee(String name) {
		/*Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("name", name));
		return (List<Employee>) criteria.list();*/
		CriteriaQuery<Employee> createQuery = emp.getCriteriaBuilder().createQuery(Employee.class);
		Root<Employee> root = createQuery.from(Employee.class);
		createQuery.where(root.get("name").in(name));
		return emp.createQuery(createQuery).getResultList();
	}
	
	public void save(Employee employee) {System.err.println(emp);
		emp.merge(employee);
	}
}
