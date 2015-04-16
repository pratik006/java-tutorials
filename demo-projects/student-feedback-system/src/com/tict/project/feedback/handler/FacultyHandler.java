package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.vo.Faculty;
import com.tict.project.feedback.vo.SemesterSubject;
import com.tict.project.feedback.vo.User;

public class FacultyHandler extends UserHandler {

	public FacultyHandler(DatabaseConnector connector) {
		super(connector);
	}
	
	public int addFaculty(Faculty faculty, List<String[]> subjects) throws SQLException {
		String query = "insert into "+FeedbackConsts.SCHEMA+".USER(UNAME, PASSWORD,FNAME,LNAME,UTYPE) VALUES('"
		+faculty.getUsername()+"', 'PASSWORD', '"+faculty.getFirstName()+"', '"+faculty.getLastName()+"', '"+FeedbackConsts.ROLE_FACULTY+"');";
		
		int resp = 0;
		long facultyId = connector.createNew(query);
		query = "";
		for(SemesterSubject ss : faculty.getSemesterSubjects()) {
			query = "insert into "+FeedbackConsts.SCHEMA+".SEMESTER_SUBJECT_FACULTY(SEMESTER_ID, SUBJECT_ID, FACULTY_ID) VALUES("
					+ ss.getSemesterId() + ", "
					+ ss.getSubjectId() + ", "						
					+ facultyId					
					+ ");";
			resp += connector.executeUpdate(query);
		}
		connector.commit();
		return resp;
	}
	
	public List<User> getAllFaculties() {
		String query = "select * from "+FeedbackConsts.SCHEMA+".USER WHERE UTYPE='"+FeedbackConsts.ROLE_FACULTY+"'";
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
	
	String facForSubCourseQuery = "select * from FEEDBACK.USER where ID=(select FACULTY_ID from "+FeedbackConsts.SCHEMA+".FAC_SUB_COURSE_XREF where SUBJECT_ID=? and course_id=(select COURSE_ID from feedback.STUDENT_COURSE_ENROLL where student_id=?))";
	public User getFaculty(long subjectId, long studentId) throws SQLException {
		ResultSet rs = connector.prepareStatement(facForSubCourseQuery, new Object[]{subjectId, studentId});
		if(rs.next()) {
			User faculty = mapUserFromRS(rs);
			return faculty;
		}
		return null;
	}
}
