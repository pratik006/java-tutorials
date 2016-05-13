package com.prapps.ejb.client;

import java.net.MalformedURLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.prapps.student.api.HelloWorldBeanRemote;
import com.prapps.student.api.Student;
import com.prapps.student.api.StudentSearchCriteria;

public class EjbClientTest {
	private static final Logger LOG = Logger.getLogger(EjbClientTest.class);
	
    public static void main( String[] args ) throws MalformedURLException
    {
    	testEJB();
    }
    
    private static void testEJB() {
    	int ctr = 1;
    	while(ctr > 0) {
    		HelloWorldBeanRemote bean = doLookup();
    		StudentSearchCriteria request = new StudentSearchCriteria();
    		List<Student> students = bean.searchStudents(request);
    		LOG.trace("Response from EJB: "+students);
    		for (Student student : students) {
    			LOG.debug(student.getFirstName()+"\t"+student.getLastName());
    		}
    		
    		Student student = new Student();
    		student.setId(501l);
    		student.setFirstName("John");
    		student.setLastName("Doe");
    		
    		bean.registerStudent(student);
    		
    		
    		students = bean.searchStudents(request);
    		LOG.trace("Response from EJB: "+students);
    		for (Student std : students) {
    			LOG.debug(std.getFirstName()+"\t"+std.getLastName());
    		}
    		
    		ctr--;
    	}
    	
    }
    
    private static HelloWorldBeanRemote doLookup() {
		HelloWorldBeanRemote bean = null;
		try {
			Context ctx = ClientUtility.getInitialContext();			
			String lookupName = getLookupName();
			
			Object obj = ctx.lookup(lookupName);
			bean = (HelloWorldBeanRemote) obj;
			//ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}

	private static String getLookupName() {
		/*
		 * The app name is the EAR name of the deployed EJB without .ear suffix.
		 * Since we haven't deployed the application as a .ear, the app name for
		 * us will be an empty string
		 */
		String appName = "hello";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = "hello-ejb";

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This
		 * can be an empty string if distinct name is not specified.
		 */
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = "HelloService";

		// Fully qualified remote interface name
		final String interfaceName = HelloWorldBeanRemote.class.getName();

		// Create a look up string name
		/*String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;*/
		String name = appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;

		return name;
	}
}
