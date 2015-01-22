package com.prapps.tutorial.guice.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.prapps.tutorial.guice.dao.api.EmployeeDao;
import com.prapps.tutorial.guice.entities.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Inject private Provider<EntityManager> emp;
	
	private Session getSession() {
		return emp.get().unwrap(Session.class);
	}
	
	public Employee findEmployeeById(long id) {
		return emp.get().find(Employee.class, id);
	}
	
	public List<Employee> findEmployee(String name) {
		/*Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("name", name));
		return (List<Employee>) criteria.list();*/
		CriteriaQuery<Employee> createQuery = emp.get().getCriteriaBuilder().createQuery(Employee.class);
		Root<Employee> root = createQuery.from(Employee.class);
		createQuery.where(root.get("name").in(name));
		return emp.get().createQuery(createQuery).getResultList();
	}
	
	public void save(Employee employee) {
		emp.get().merge(employee);
	}
}
