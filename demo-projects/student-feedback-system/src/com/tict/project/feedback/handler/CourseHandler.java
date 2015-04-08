package com.tict.project.feedback.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.vo.Course;

public class CourseHandler extends AbstractHandler {

	public CourseHandler(DatabaseConnector connector) {
		super(connector);
	}
	
	public List<Course> getAllCourse() {
		List<Course> courses = new ArrayList<Course>();
		String query = "select id, name from "+FeedbackConsts.SCHEMA+".COURSE";
		try {
			ResultSet rs = connector.executeQuery(query);
			while(rs.next()) {
				Course course = mapCourseFromRS(rs); 
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}
	
	private Course mapCourseFromRS(ResultSet rs) throws SQLException {
		Course course = new Course();
		course.setId(rs.getLong("id"));
		course.setName(rs.getString("name"));
		return course;
	}
	
	public List<Course> getCourseForFaculty(long facultyId) {
		List<Course> courses = new ArrayList<Course>();
		String query = "select T1.ID, T1.name from "+FeedbackConsts.SCHEMA+".COURSE T1, "+FeedbackConsts.SCHEMA+".FAC_SUB_COURSE_XREF T2 where FACULTY_ID="+facultyId
				+" AND T1.ID=T2.COURSE_ID";
		try {
			ResultSet rs = connector.executeQuery(query);
			while(rs.next()) {
				Course course = mapCourseFromRS(rs); 
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}
}
