package com.tict.project.feedback.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.handler.CourseHandler;
import com.tict.project.feedback.handler.FacultyHandler;
import com.tict.project.feedback.handler.FeedbackHandler;
import com.tict.project.feedback.handler.LoginHandler;
import com.tict.project.feedback.handler.StudentHandler;
import com.tict.project.feedback.handler.SubjectHandler;
import com.tict.project.feedback.handler.UserHandler;
import com.tict.project.feedback.vo.User;

public abstract class AbstractController {

	protected DatabaseConnector connector;
	protected LoginHandler loginHandler;
	protected UserHandler userHandler;
	protected StudentHandler studentHandler;
	protected CourseHandler courseHandler;
	protected SubjectHandler subjectHandler;
	protected FacultyHandler facultyHandler;
	protected FeedbackHandler feedbackHandler;
	
	public AbstractController() throws ClassNotFoundException, IOException, SQLException {
		connector = new DatabaseConnector();
    	loginHandler = new LoginHandler(connector);
    	userHandler = new UserHandler(connector);
    	studentHandler = new StudentHandler(connector);
    	courseHandler = new CourseHandler(connector);
    	subjectHandler = new SubjectHandler(connector);
    	facultyHandler = new FacultyHandler(connector);
    	feedbackHandler = new FeedbackHandler(connector);
	}
	
	abstract public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	protected User getUserInfo(HttpServletRequest req) {
		return (User)req.getSession().getAttribute("user");
	}
	
	protected void addErrorMsg(HttpServletRequest request, String msg) {
		request.setAttribute(FeedbackConsts.ERROR_MSG, msg);
	}
	
	protected void addMsg(HttpServletRequest request, String msg) {
		request.setAttribute(FeedbackConsts.MSG, msg);
	}
	
	protected String getAction(HttpServletRequest request) {
		String action = request.getParameter("action");
		return action;
	}
	
	public String handleDefaultRequest(HttpServletRequest request, HttpServletResponse response) {
		return "WEB-INF/Home.jsp";
	}
	
	public String changePassword() {
		return "WEB-INF/ChangePassword.jsp";
	}
	
}
