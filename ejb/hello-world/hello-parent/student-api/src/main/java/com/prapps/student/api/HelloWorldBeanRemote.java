package com.prapps.student.api;

import java.util.List;

import javax.ejb.Remote;

import com.prapps.student.api.Student;
import com.prapps.student.api.StudentSearchCriteria;

@Remote
public interface HelloWorldBeanRemote {

	List<Student> searchStudents(StudentSearchCriteria request);
	void registerStudent(Student request);
	
}
