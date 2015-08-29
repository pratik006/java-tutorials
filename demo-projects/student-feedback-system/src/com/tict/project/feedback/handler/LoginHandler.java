package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;

public class LoginHandler {

	private DatabaseConnector connector;
	
	public LoginHandler(DatabaseConnector connector) {
		this.connector = connector;
	}
	
	public boolean login(String username, String password) {
		try {
			ResultSet rs = connector.executeQuery("select uname, password from "+FeedbackConsts.SCHEMA+".USER where uname ='"+username+"'");
			if(rs.next()) {
				if(password.equals(rs.getString("password"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
