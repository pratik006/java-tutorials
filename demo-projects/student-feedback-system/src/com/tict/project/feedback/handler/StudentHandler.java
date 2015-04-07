package com.tict.project.feedback.handler;

import java.sql.SQLException;

import com.tict.project.feedback.DatabaseConnector;
import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.vo.Student;
import com.tict.project.feedback.vo.User;

public class StudentHandler extends UserHandler {

	public StudentHandler(DatabaseConnector connector) {
		super(connector);
	}
	
	public void saveStudent(Student user) throws SQLException {
		User existingUser = getUser(user.getUsername());
		if(existingUser == null) {
			//New Student, need to insert
			connector.executeUpdate("insert into "+FeedbackConsts.SCHEMA+".user(uname,password,fname,lname,utype) values('"+
					user.getUsername()+"', 'password', '"+user.getFirstName()+"', '"+user.getLastName()+"', '"+user.getType()+"')");
			
			connector.executeUpdate("insert into "+FeedbackConsts.SCHEMA+".STUDENT_COURSE_ENROLL(student_id, course_id) values("+user.getId()+","+user.getCourseId()+")");
		}
		else {
			//old Student, just update record
			connector.executeUpdate("update "+FeedbackConsts.SCHEMA+".user set uname='"+
					user.getUsername()+"', fname='"+user.getFirstName()+"', lname='"+user.getLastName()+"' where uname='"+user.getUsername()+"'");
		}
	}
}
