package org.hello.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.prapps.hello.client.stub.Hello;
import com.prapps.hello.client.stub.HelloRequest;
import com.prapps.hello.client.stub.HelloService;
import com.prapps.hello.client.stub.ObjectFactory;
import com.prapps.hello.ejb.HelloWorld;
import com.prapps.hello.ejb.HelloWorldBeanRemote;

/**
 * Hello world!
 *
 */
public class HelloClientTest 
{
    public static void main( String[] args ) throws MalformedURLException
    {
    	HelloWorldBeanRemote bean = doLookup();
		System.out.println("Response from EJB: "+bean.sayHelloRemote());
		
		HelloService hs = new HelloService();
		System.out.println("Web Service Response: "+ hs.getHelloPort().sayHelloRemote());
		HelloRequest request = new HelloRequest();
		request.setId(5l);
		request.setKey("hi");
		System.out.println(hs.getHelloPort().sayHelloRemoteDetail(request).getResp());
		/*Service service = Service.create(                                 
				  new URL("http://localhost:8080/hello-ejb-0.0.1-SNAPSHOT/HelloService/Hello?wsdl"), 
				  new QName("HelloService")
				);      
				Hello hello = service.getPort(Hello.class);    
				System.out.println(hello.sayHelloRemote());*/
    }
    
    private static HelloWorldBeanRemote doLookup() {
		HelloWorldBeanRemote bean = null;
		try {
			//context = ClientUtility.getInitialContext();
			String lookupName = getLookupName();
			
			Properties jndiProps = new Properties();
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProps.put(Context.PROVIDER_URL,"remote://localhost:4447");
			// username
			jndiProps.put(Context.SECURITY_PRINCIPAL, "appuser");
			// password
			jndiProps.put(Context.SECURITY_CREDENTIALS, "password123");
			// This is an important property to set if you want to do EJB invocations via the remote-naming project
			jndiProps.put("jboss.naming.client.ejb.context", true);
			// create a context passing these properties
			Context ctx = new InitialContext(jndiProps);
			Object obj = ctx.lookup(lookupName);
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
		String appName = "hello";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = "hello";

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
