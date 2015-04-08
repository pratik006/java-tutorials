package com.tict.project.feedback.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.vo.Course;
import com.tict.project.feedback.vo.User;

public class AdminController extends AbstractController {

	public AdminController() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String action = getAction(request);
		User user = getUserInfo(request);
		
		if("addStudent".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				List<Course> courses = courseHandler.getAllCourse();
				request.setAttribute("courses", courses);
				return "WEB-INF/AddStudent.jsp";
			}
		}
		return null;
	}
}
