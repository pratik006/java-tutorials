package com.tict.project.feedback.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnector {

	private Connection connection;
	private Statement stmt;
	private Properties connectionProp;
	
	public DatabaseConnector() throws IOException, SQLException, ClassNotFoundException {
		connectionProp = new Properties();
		connectionProp.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
		String url = connectionProp.getProperty("url");
		String username = connectionProp.getProperty("username");
		String password = connectionProp.getProperty("password");
		String driverClassName = connectionProp.getProperty("driverClassName");
		Class.forName(driverClassName);
		connection = DriverManager.getConnection(url, username, password);
		connection.setAutoCommit(false);
		System.out.println("connection created.");
		stmt = connection.createStatement();
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		System.out.println("executeQuery query: "+query);
		return stmt.executeQuery(query);
	}
	
	public void executeUpdate(String query) throws SQLException {
		System.out.println("executeUpdate query: "+query);
		stmt.executeUpdate(query);
	}
	
	public void close() throws SQLException {
		stmt.close();
		connection.close();
	}
	
	public void commit() throws SQLException {
		connection.commit();
	}
	
	public void rollback() throws SQLException {
		connection.rollback();
	}
}
