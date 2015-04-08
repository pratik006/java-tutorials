package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
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
	
	public List<User> getAllFaculties() {
		String query = "select * from "+FeedbackConsts.SCHEMA+".USER WHERE UTYPE='FACULTY'";
		List<User> faculties = new ArrayList<User>();
		try {
			ResultSet rs = connector.executeQuery(query);
			while(rs.next()) {
				User user = mapUserFromRS(rs);
				faculties.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return faculties;
	}
}
