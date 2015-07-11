package com.ibytecode.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Test2 {

	public static void main(String[] args) {
		Test2 client = new Test2();
        client.doLookups();

        System.out.println("Done!");

	}

	private static String[] JNDINAME = {
        "jboss-as-ejb-remote-app/CalculatorBean!org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator", 
        "ejb:/jboss-as-ejb-remote-app/CalculatorBean!org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator" 
   };

   private Hashtable jndiProps;

   public Test2() {
     // setup 'base' jndi properties - no jboss-ejb-client.properties being picked up from classpath!
     jndiProps = new Hashtable();
        jndiProps.put("java.naming.factory.initial",
"org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put(InitialContext.PROVIDER_URL, "remote://localhost:4447");
        jndiProps.put("jboss.naming.client.ejb.context", true);

        // needed for remote access - remember to run add-user.bat
        jndiProps.put(Context.SECURITY_PRINCIPAL, "client");
        jndiProps.put(Context.SECURITY_CREDENTIALS, "password");
   }

  public void doLookups() {
       // the 'exported' namespace
       for (int i = 0; i < JNDINAME.length; i++) {
             lookup(JNDINAME[i]);
        }

       // This is an important property to set if you want to do EJB invocations via the remote-naming project
      jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

      // now with the ejb
      for (int i = 0; i < JNDINAME.length; i++) {
             lookup(JNDINAME[i]);
        }
  }

  private void lookup(String name) {
       System.out.println("Lookup name="+name);

       Context ctx = null;
      try {
             ctx = new InitialContext(jndiProps);
             Object ref = ctx.lookup(name);
             System.out.println("...Successful");
        } catch (NamingException e) {
             System.out.println("...Failed");
             //System.out.println(e.getMessage());
             e.printStackTrace();
        } finally {
             if (ctx != null) {
                  try {
                       ctx.close();
                  } catch (NamingException e) {}
             }
        }
  }
}
