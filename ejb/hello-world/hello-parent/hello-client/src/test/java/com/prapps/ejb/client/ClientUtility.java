package com.prapps.ejb.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientUtility {

	private static Context initialContext;
	 
    public static Context getInitialContext() throws NamingException {
        if (initialContext == null) {
            Properties properties = new Properties();
            try {
				properties.load(new FileInputStream("src/main/resources/jboss-ejb-client.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
            initialContext = new InitialContext(properties);
        }
        return initialContext;
    }
}
