package com.prapps.hello.persistence.mapper.student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prapps.hello.persistence.entity.StudentEntity;
import com.prapps.student.api.Student;

public class StudentMapper {

	public Student mapStudent(StudentEntity entity) {
		Student student = new Student();
		student.setId(entity.getId());
		student.setFirstName(entity.getFirstName());
		student.setLastName(entity.getLastName());
		return student;
	}
	
	public List<Student> mapStudents(Collection<StudentEntity> studentEntities) {
		List<Student> students = new ArrayList<>(studentEntities.size());
		for (StudentEntity entity : studentEntities) {
			students.add(mapStudent(entity));
		}
		return students;
	}
}
