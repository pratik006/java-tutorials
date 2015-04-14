package com.tict.project.feedback.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tict.project.feedback.vo.Course;
import com.tict.project.feedback.vo.FeedbackConfigParam;
import com.tict.project.feedback.vo.Subject;
import com.tict.project.feedback.vo.User;

public class FacultyController extends AbstractController {

	public FacultyController() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String action = getAction(request);
		User user = getUserInfo(request);
		String view = null;
		
		if("viewFeedbackForm".equals(action)) {
			List<Course> courses = courseHandler.getCourseForFaculty(user.getId());
			request.setAttribute("courses", courses);
			List<Subject> subjects = subjectHandler.getSubjectsForFaculty(user.getId());
			request.setAttribute("subjects", subjects);
			view = "WEB-INF/ViewFeedback.jsp";
		}
		else if("viewFeedback".equals(action)) {
			List<Course> courses = courseHandler.getCourseForFaculty(user.getId());
			request.setAttribute("courses", courses);
			List<Subject> subjects = subjectHandler.getSubjectsForFaculty(user.getId());
			request.setAttribute("subjects", subjects);
			
			String[][] results;
			try {
				results = feedbackHandler.viewFeedback();
				request.setAttribute("results", results);
				view = "WEB-INF/ViewFeedback.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				view = "Error.jsp";
			}
			
		}
		
		if(view == null) {
			System.err.println("FacultyController -> Cannot handle this request. action: "+action);
		}
		return view;
	}

}
