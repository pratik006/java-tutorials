package com.prapps.ejb.client;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.prapps.student.IStudentServiceService;
import com.prapps.student.StudentDetail;
import com.prapps.student.StudentRegnRequest;
import com.prapps.student.StudentRegnResponse;
import com.prapps.student.StudentSearchRequest;
import com.prapps.student.StudentSearchResponse;

/**
 * Hello world!
 *
 */
public class HelloWebServiceClientTest {
	private static final Logger LOG = Logger.getLogger(HelloWebServiceClientTest.class);
	
    public static void main( String[] args ) throws MalformedURLException
    {
    	testSearchStudentService();
    	testRegnStudentService();
    	LOG.trace("Student added.");
    	testSearchStudentService();
    	int ctr = 1;
    	/*while(ctr-- > 0) {
    		testSearchStudentService();
    	}*/
    }
    
    private static void testSearchStudentService() throws MalformedURLException {
    	IStudentServiceService service = new IStudentServiceService();
    	StudentSearchRequest studentSearchRequest = new StudentSearchRequest();
    	StudentSearchResponse response = service.getIStudentServicePort().searchStudents(studentSearchRequest);
    	LOG.debug("Student Details...\n-------------------------");
    	for (StudentDetail student : response.getStudents()) {
    		LOG.debug(student.getId()+"\t"+student.getFirstName()+"\t"+student.getLastName());
    	}
    }
    
    private static void testRegnStudentService() {
    	IStudentServiceService service = new IStudentServiceService();
    	StudentRegnRequest studentRegnRequest = new StudentRegnRequest();
    	StudentDetail detail = new StudentDetail();
    	detail.setId(2L);
    	detail.setFirstName("Abc");
    	detail.setLastName("Xyz");
    	studentRegnRequest.setStudent(detail);
    	StudentRegnResponse resp = service.getIStudentServicePort().registerStudent(studentRegnRequest);
    	LOG.debug("StudentRegnResponse: " + resp.isResult());
    }
}
