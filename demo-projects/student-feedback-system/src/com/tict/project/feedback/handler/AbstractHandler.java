package com.tict.project.feedback.handler;

import com.tict.project.feedback.DatabaseConnector;

public class AbstractHandler {

	protected DatabaseConnector connector;
	
	public AbstractHandler(DatabaseConnector connector) {
		this.connector = connector;
	}
}
