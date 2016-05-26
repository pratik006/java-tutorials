package com.prapps.hello.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.prapps.hello.ejb.mapper.student.StudentMapper;
import com.prapps.hello.persistence.dao.StudentDao;
import com.prapps.student.api.Student;
import com.prapps.student.api.StudentBeanRemote;
import com.prapps.student.api.StudentSearchCriteria;
import com.prapps.student.generated.IStudentService;
import com.prapps.student.generated.StudentRegnRequest;
import com.prapps.student.generated.StudentRegnResponse;
import com.prapps.student.generated.StudentSearchRequest;
import com.prapps.student.generated.StudentSearchResponse;

/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
@Remote(StudentBeanRemote.class)
@WebService(name="IStudentService", 
	serviceName="IStudentService", 
	endpointInterface="com.prapps.student.generated.IStudentService", 
	targetNamespace="http://student.prapps.com/", 
	wsdlLocation="META-INF/wsdls/student/student.wsdl")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
@Interceptors(TimerLog.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class StudentService implements StudentBeanRemote, IStudentService {

	@Inject StudentDao studentDao;
	@Inject StudentMapper studentMapper;
	
	@Override
	@WebMethod
	public StudentSearchResponse searchStudents(StudentSearchRequest studentSearchRequest) {
		StudentSearchCriteria request = new StudentSearchCriteria();
		request.setId(studentSearchRequest.getId());
		request.setFirstName(studentSearchRequest.getFirstName());
		List<Student> list = searchStudents(request);
		StudentSearchResponse response = new StudentSearchResponse();
		for (Student student : list) {
			response.getStudents().add(studentMapper.mapStudent(student));	
		}
		return response;
	}
	
	@Override
	@WebMethod
	public StudentRegnResponse registerStudent(StudentRegnRequest request) {
		registerStudent(studentMapper.mapStudent(request.getStudent()));
		StudentRegnResponse resp = new StudentRegnResponse();
		resp.setResult(true);
		return resp;
	}
	
	@Override
	public List<Student> searchStudents(StudentSearchCriteria request) {
		List<Student> list = studentDao.getStudents();
		return list;
	}
	
	@Override
	public void registerStudent(Student student) {
		studentDao.saveStudent(student);
	}
	
}
