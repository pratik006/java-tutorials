package com.prapps.hello.persistence.dao;

import java.util.List;

import javax.inject.Inject;

import com.prapps.hello.persistence.Database;
import com.prapps.hello.persistence.entity.StudentEntity;
import com.prapps.hello.persistence.mapper.student.StudentMapper;
import com.prapps.student.api.Student;

public class StudentDao {

	@Inject Database context;
	@Inject private StudentMapper studentMapper;
	
	public List<Student> getStudents() {
		List<StudentEntity> students = context.getSession().createCriteria(StudentEntity.class).list();
		return studentMapper.mapStudents(students);
	}
}
