package com.prapps.ejb.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prapps.student.api.Student;
import com.prapps.student.api.StudentBeanRemote;
import com.prapps.student.api.StudentSearchCriteria;

import junit.framework.Assert;

public class EjbClientTest {
	private static final Logger LOG = Logger.getLogger(EjbClientTest.class);
	
	private Context ctx;
	private StudentBeanRemote studentBeanRemote;
	
	@Before
	public void setUp() throws NamingException {
		if (ctx == null) {
            Properties properties = new Properties();
            try {
				properties.load(new FileInputStream("src/test/resources/jboss-ejb-client.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
            ctx = new InitialContext(properties);
        }
		String lookupName = getLookupName();
		Object obj = ctx.lookup(lookupName);
		studentBeanRemote = (StudentBeanRemote) obj;
	}
	
	@After
	public void tearDown() throws NamingException {
		ctx.close();
	}
	
	@Test
	public void testRegisterStudent() {
		Student student = new Student();
		//student.setId(501l);
		student.setFirstName("John");
		student.setLastName("Dykes");
		
		studentBeanRemote.registerStudent(student);
		LOG.trace("Student "+student.getFirstName()+" "+student.getLastName()+" registered successfully.");
		
		StudentSearchCriteria request = new StudentSearchCriteria();
		List<Student> students = studentBeanRemote.searchStudents(request);
		Set<String> fNames = new HashSet<>();
		for (Student std : students) {
			fNames.add(std.getFirstName());
		}
		LOG.trace("Student searched: "+fNames.contains(student.getFirstName()));
		Assert.assertTrue(fNames.contains(student.getFirstName()));
	}
	
    
	private static String getLookupName() {
		/*
		 * The app name is the EAR name of the deployed EJB without .ear suffix.
		 * Since we haven't deployed the application as a .ear, the app name for
		 * us will be an empty string
		 */
		String appName = "student";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = "student-ejb";

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This
		 * can be an empty string if distinct name is not specified.
		 */
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = "StudentService";

		// Fully qualified remote interface name
		final String interfaceName = StudentBeanRemote.class.getName();

		// Create a look up string name
		/*String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;*/
		String name = appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;

		return name;
	}
}
