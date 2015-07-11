package com.ibytecode.client;

import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.ejb.client.StatelessEJBLocator;

import com.prapps.HelloWorld;
import com.prapps.HelloWorldBeanLocal;
import com.prapps.HelloWorldBeanRemote;

public class EJBApplicationClient {

	public static void main(String[] args) {
		System.out.println();
		HelloWorldBeanRemote bean = doLookup();
		System.out.println("Response from EJB: "+bean.sayHelloRemote()); // 4. Call business logic
	}

	private static HelloWorldBeanRemote doLookup() {
		Context context = null;
		HelloWorldBeanRemote bean = null;
		try {
			// 1. Obtaining Context
			context = ClientUtility.getInitialContext();
			System.out.println(context);
			// 2. Generate JNDI Lookup name
			String lookupName = getLookupName();
			System.out.println(lookupName);
			// 3. Lookup and cast
			Object obj = context.lookup(lookupName);
			bean = (HelloWorldBeanRemote) obj;
			System.out.println(bean);
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
		String appName = "";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = "test";

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This
		 * can be an empty string if distinct name is not specified.
		 */
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = HelloWorld.class.getSimpleName();

		// Fully qualified remote interface name
		final String interfaceName = HelloWorldBeanRemote.class.getName();

		// Create a look up string name
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;

		return name;
	}
}
