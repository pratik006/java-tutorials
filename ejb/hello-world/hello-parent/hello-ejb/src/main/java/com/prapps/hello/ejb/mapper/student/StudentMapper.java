package com.prapps.hello.ejb.mapper.student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prapps.student.api.Student;

public class StudentMapper {

	public com.prapps.generated.Student mapStudent(Student studentDto) {
		com.prapps.generated.Student student = new com.prapps.generated.Student();
		student.setId(studentDto.getId());
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		return student;
	}
	
	public List<com.prapps.generated.Student> mapStudents(Collection<Student> studentList) {
		List<com.prapps.generated.Student> students = new ArrayList<>(studentList.size());
		for (Student entity : studentList) {
			students.add(mapStudent(entity));
		}
		return students;
	}
}
