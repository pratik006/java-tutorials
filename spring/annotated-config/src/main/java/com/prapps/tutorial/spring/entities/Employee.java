package com.prapps.tutorial.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
	@Column(name="ID")
	private long id;
	@Column(name="NAME")
	private String name;
	@Column(name="SALARY")
	private double salary;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(long id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "entity Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	
}
