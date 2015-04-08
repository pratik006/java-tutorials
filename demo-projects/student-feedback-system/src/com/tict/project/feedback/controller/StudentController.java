package com.tict.project.feedback.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tict.project.feedback.vo.Course;
import com.tict.project.feedback.vo.Subject;
import com.tict.project.feedback.vo.User;

public class StudentController extends AbstractController {

	public StudentController() throws ClassNotFoundException, IOException, SQLException {
		super();
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String action = getAction(request);
		User user = getUserInfo(request);
		String view = null;
		
		if("addFeedback".equals(action)) {
			List<User> faculties = facultyHandler.getAllFaculties();
			request.setAttribute("faculties", faculties);
			List<Subject> subjects = subjectHandler.getAllSubjects();
			request.setAttribute("subjects", subjects);
			List<Course> courses = courseHandler.getAllCourse();
			request.setAttribute("courses", courses);
			view = "WEB-INF/StudentFeedback.jsp";
		}
		else if("saveFeedback".equals(action)) {
			try {
				feedbackHandler.saveFeedback(request, user.getId());
				view = "WEB-INF/SuccessfulFeedback.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				view = "Error.jsp";
			}
			
		}
		
		if(view == null) {
			System.err.println("StudentController -> Cannot handle this request. action: "+action);
		}
		return view;
	}
}
