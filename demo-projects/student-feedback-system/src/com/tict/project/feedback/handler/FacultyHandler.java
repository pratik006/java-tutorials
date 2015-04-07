package com.tict.project.feedback.handler;

import java.sql.SQLException;
import java.util.List;

import com.tict.project.feedback.DatabaseConnector;
import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.vo.User;

public class FacultyHandler extends UserHandler {

	public FacultyHandler(DatabaseConnector connector) {
		super(connector);
	}
	
	public void addFaculty(User faculty, List<String[]> subjects) throws SQLException {
		saveUser(faculty);
		String query = "insert into "+FeedbackConsts.SCHEMA+".FAC....";
		connector.executeUpdate(query);
	}
}
