package com.prapps.hello.persistence.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.prapps.hello.persistence.Database;
import com.prapps.hello.persistence.entity.StudentEntity;
import com.prapps.hello.persistence.mapper.student.StudentMapper;
import com.prapps.student.api.Student;

@Stateless
@LocalBean
public class StudentDao {
	private static Logger LOG = Logger.getLogger(StudentDao.class);

	@Inject Database context;
	@Inject private StudentMapper studentMapper;
	
	public List<Student> getStudents() {
		LOG.debug("Retrieving Students");
		List<StudentEntity> students = context.getSession().createCriteria(StudentEntity.class).list();
		LOG.debug(students);
		return studentMapper.mapStudents(students);
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void saveStudent(Student student) {
		StudentEntity entity = studentMapper.mapStudent(student);
		entity.setId(null);
		context.getSession().save(entity);
	}
}
