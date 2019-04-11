package com.prapps.tutorial.spring.netflix.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    public Student() { }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @OneToMany
    private Set<Course> registeredCourses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Course> getRegisteredCourses() {
        if (registeredCourses == null) {
            registeredCourses = new HashSet<>();
        }

        return registeredCourses;
    }

    public void setRegisteredCourses(Set<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
}
