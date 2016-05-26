package com.prapps.ejb.client;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.prapps.student.IStudentService_Service;
import com.prapps.student.StudentDetail;
import com.prapps.student.StudentRegnRequest;
import com.prapps.student.StudentRegnResponse;
import com.prapps.student.StudentSearchRequest;
import com.prapps.student.StudentSearchResponse;
import com.prapps.student.api.Student;

import junit.framework.Assert;

/**
 * Hello world!
 *
 */
public class StudentWebServiceClientTest {
	private static final Logger LOG = Logger.getLogger(StudentWebServiceClientTest.class);
	
    @Test
    public void testSearchStudentService() throws MalformedURLException {
    	IStudentService_Service service = new IStudentService_Service();
    	StudentSearchRequest studentSearchRequest = new StudentSearchRequest();
    	StudentSearchResponse response = service.getStudentPort().searchStudents(studentSearchRequest);
    	LOG.debug("Student Details...\n-------------------------");
    	for (StudentDetail student : response.getStudents()) {
    		LOG.debug(student.getId()+"\t"+student.getFirstName()+"\t"+student.getLastName());
    	}
    }
    
    @Test
    public void testRegnStudentService() {
    	IStudentService_Service service = new IStudentService_Service();
    	StudentRegnRequest studentRegnRequest = new StudentRegnRequest();
    	StudentDetail detail = new StudentDetail();
    	detail.setId(2L);
    	detail.setFirstName("Abc");
    	detail.setLastName("Xyz");
    	studentRegnRequest.setStudent(detail);
    	StudentRegnResponse resp = service.getStudentPort().registerStudent(studentRegnRequest);
    	LOG.debug("StudentRegnResponse: " + resp.isResult());
    	
    	StudentSearchRequest studentSearchRequest = new StudentSearchRequest();
    	StudentSearchResponse response = service.getStudentPort().searchStudents(studentSearchRequest);
    	LOG.debug("Student Details...\n-------------------------");
    	Set<String> fNames = new HashSet<>();
		for (StudentDetail std : response.getStudents()) {
			fNames.add(std.getFirstName());
			LOG.debug(std.getId()+"\t"+std.getFirstName()+"\t"+std.getLastName());
		}
		LOG.trace("Student searched: "+fNames.contains(detail.getFirstName()));
		Assert.assertTrue(fNames.contains(detail.getFirstName()));
    }
}
