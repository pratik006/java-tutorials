package com.prapps.ejb.client;

import java.net.MalformedURLException;

import javax.naming.Context;
import javax.naming.NamingException;

import com.prapps.hello.ejb.HelloRequest;
import com.prapps.hello.ejb.HelloService;
import com.prapps.hello.ejb.HelloWorldBeanRemote;

public class EjbClientTest {
    public static void main( String[] args ) throws MalformedURLException
    {
    	testEJB();
    }
    
    private static void testEJB() {
    	HelloWorldBeanRemote bean = doLookup();
		System.out.println("Response from EJB: "+bean.sayHelloRemote());
		HelloRequest helloRequest = new HelloRequest();
		helloRequest.setId(501l);
		helloRequest.setKey("World");
		//bean = doLookup();
		System.out.println(bean.sayHelloRemoteDetail(helloRequest).getResp());
    }
    
    private static HelloWorldBeanRemote doLookup() {
		HelloWorldBeanRemote bean = null;
		try {
			Context ctx = ClientUtility.getInitialContext();			
			String lookupName = getLookupName();
			
			/*Properties jndiProps = new Properties();
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProps.put(Context.PROVIDER_URL,"remote://localhost:4447");
			jndiProps.put(Context.SECURITY_PRINCIPAL, "appuser");
			jndiProps.put(Context.SECURITY_CREDENTIALS, "password123");
			// This is an important property to set if you want to do EJB invocations via the remote-naming project
			jndiProps.put("jboss.naming.client.ejb.context", true);
			// create a context passing these properties
			Context ctx = new InitialContext(jndiProps);*/
			Object obj = ctx.lookup(lookupName);
			bean = (HelloWorldBeanRemote) obj;
			//context.close();
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
		String beanName = HelloService.class.getSimpleName();

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
