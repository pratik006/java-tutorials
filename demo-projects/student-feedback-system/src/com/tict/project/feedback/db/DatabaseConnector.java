package com.tict.project.feedback.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

public class DatabaseConnector {

	private Connection connection;
	private Connection writeConnection;
	private Statement stmt;
	private Properties connectionProp;
	
	public DatabaseConnector() throws IOException, SQLException, ClassNotFoundException {
		connectionProp = new Properties();
		connectionProp.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
		connection = createConnection(connectionProp);
		writeConnection = createConnection(connectionProp);
		writeConnection.setAutoCommit(false);
		System.out.println("connection created.");
		stmt = connection.createStatement();
	}
	
	private Connection createConnection(Properties connectionProp) throws ClassNotFoundException, SQLException {
		String url = connectionProp.getProperty("url");
		String username = connectionProp.getProperty("username");
		String password = connectionProp.getProperty("password");
		String driverClassName = connectionProp.getProperty("driverClassName");
		Class.forName(driverClassName);
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		System.out.println("executeQuery query: "+query);
		return stmt.executeQuery(query);
	}
	
	public int executeUpdate(String query) throws SQLException {
		System.out.println("executeUpdate query: "+query);
		return writeConnection.createStatement().executeUpdate(query);
	}
	
	public long createNew(String query) throws SQLException {
		System.out.println("createNew query: "+query);
		long id = writeConnection.createStatement().executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()) {
			id = rs.getLong(1);
		}
		rs.close();
		return id;
	}
	
	public ResultSet prepareStatement(String query, Object[] params) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(query);
		int ctr = 0;
		for(Object param : params) {
			pstmt.setObject(++ctr, param);
		}
		System.out.println("query: "+query+"\nparams: "+Arrays.toString(params));
		return pstmt.executeQuery();
	}
	
	public void close() throws SQLException {
		stmt.close();
		connection.close();
		writeConnection.close();
	}
	
	public void commit() throws SQLException {
		writeConnection.commit();
	}
	
	public void rollback() throws SQLException {
		writeConnection.rollback();
	}
}
