package com.tict.project.feedback.handler;

import java.sql.SQLException;

import com.tict.project.feedback.DatabaseConnector;

public class FeedbackHandler {

	private DatabaseConnector connector;
	
	public FeedbackHandler(DatabaseConnector connector) {
		this.connector = connector;
	}
	
	public void viewFeedback(long facultyId) {
		try {
			connector.executeQuery("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
