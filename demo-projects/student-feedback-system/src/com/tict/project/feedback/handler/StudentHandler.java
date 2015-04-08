package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.vo.Student;
import com.tict.project.feedback.vo.User;

public class StudentHandler extends UserHandler {

	public StudentHandler(DatabaseConnector connector) {
		super(connector);
	}
	
	public void saveStudent(Student user) throws SQLException {
		User existingUser = getUser(user.getUsername());
		try {
			if(existingUser == null) {
				//New Student, need to insert
				connector.executeUpdate("insert into "+FeedbackConsts.SCHEMA+".user(uname,password,fname,lname,utype) values('"+
						user.getUsername()+"', 'password', '"+user.getFirstName()+"', '"+user.getLastName()+"', '"+user.getType()+"')");
				ResultSet rs = connector.executeQuery("select id from feedback.user where uname = '"+user.getUsername()+"'");
				if(rs.next()) {
					long userId = rs.getLong("ID");
					connector.executeUpdate("insert into "+FeedbackConsts.SCHEMA+".STUDENT_COURSE_ENROLL(student_id, course_id) values("+userId+","+user.getCourseId()+")");
				}
				else {
					throw new SQLException("User Id not found");
				}
			}
			else {
				//old Student, just update record
				connector.executeUpdate("update "+FeedbackConsts.SCHEMA+".user set uname='"+
						user.getUsername()+"', fname='"+user.getFirstName()+"', lname='"+user.getLastName()+"' where uname='"+user.getUsername()+"'");
			}
			connector.commit();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			try {
				connector.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw ex;
		}
	}
}
