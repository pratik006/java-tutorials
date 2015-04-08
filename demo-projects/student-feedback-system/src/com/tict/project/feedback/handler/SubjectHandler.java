package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.vo.Subject;

public class SubjectHandler extends AbstractHandler {

	public SubjectHandler(DatabaseConnector connector) {
		super(connector);
	}
	
	public List<Subject> getAllSubjects() {
		String query = "select id, name from "+FeedbackConsts.SCHEMA+".SUBJECT";
		List<Subject> subjects = new ArrayList<Subject>();
		ResultSet rs;
		try {
			rs = connector.executeQuery(query);
			while(rs.next()) {
				Subject subject = mapSubjectFromRS(rs);
				subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return subjects;
	}
	
	private Subject mapSubjectFromRS(ResultSet rs) throws SQLException {
		Subject subject = new Subject();
		subject.setId(rs.getInt("id"));
		subject.setName(rs.getString("name"));
		return subject;
	}
	
	public List<Subject> getSubjectsForFaculty(long facultyId) {
		String query = "select T1.id, T1.name from "+FeedbackConsts.SCHEMA+".SUBJECT T1, "+FeedbackConsts.SCHEMA+".FAC_SUB_COURSE_XREF T2 where FACULTY_ID="+facultyId
				+" AND T1.ID=T2.SUBJECT_ID";
		List<Subject> subjects = new ArrayList<Subject>();
		ResultSet rs;
		try {
			rs = connector.executeQuery(query);
			while(rs.next()) {
				Subject subject = mapSubjectFromRS(rs);
				subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return subjects;
	}
}
