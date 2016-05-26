package com.prapps.hello.ejb.mapper.student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prapps.student.api.Student;
import com.prapps.student.generated.StudentDetail;

public class StudentMapper {

	public StudentDetail mapStudent(Student studentDto) {
		StudentDetail student = new StudentDetail();
		student.setId(studentDto.getId());
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		return student;
	}
	
	public List<StudentDetail> mapStudents(Collection<Student> studentList) {
		List<StudentDetail> students = new ArrayList<>(studentList.size());
		for (Student entity : studentList) {
			students.add(mapStudent(entity));
		}
		return students;
	}
	
	public Student mapStudent(StudentDetail detail) {
		Student student = new Student();
		student.setId(detail.getId());
		student.setFirstName(detail.getFirstName());
		student.setLastName(detail.getLastName());
		return student;
	}
}
