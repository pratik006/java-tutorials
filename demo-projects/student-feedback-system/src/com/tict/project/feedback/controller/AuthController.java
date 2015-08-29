package com.tict.project.feedback.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.vo.User;

public class AuthController extends AbstractController {

	public AuthController() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = getAction(request);
		String view = null;
		
		if("login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			boolean loginResult = loginHandler.login(username, password);
			if(loginResult) {
				User user = userHandler.getUser(username);
				request.getSession().setAttribute("user", user);
				if("admin".equalsIgnoreCase(user.getType())) {
					view = "WEB-INF/Home.jsp";
				}
				else if("student".equalsIgnoreCase(user.getType())) {
					view = "WEB-INF/Home.jsp";
				}
				else if("faculty".equalsIgnoreCase(user.getType())) {
					view = "WEB-INF/FacultyHome.jsp";
				}
				else {
					addErrorMsg(request, "Invalid Credentials !");
					view = FeedbackConsts.ERROR_PAGE;
				}
			}
			else {
				addErrorMsg(request, "Invalid Credentials !");
				view = FeedbackConsts.LOGIN_PAGE;
			}
		}
		else if("logout".equalsIgnoreCase(action)) {
			request.getSession().invalidate();
			view = FeedbackConsts.LOGIN_PAGE;
		}
		else {
			view = handleDefaultRequest(request, response);
		}
		
		if(view == null) {
			System.err.println("AuthController -> Cannot handle this request. action: "+action);
		}
		return view;
	}
	
}
