package com.tict.project.feedback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.controller.AdminController;
import com.tict.project.feedback.controller.AuthController;
import com.tict.project.feedback.controller.FacultyController;
import com.tict.project.feedback.controller.StudentController;
import com.tict.project.feedback.db.DatabaseConnector;
import com.tict.project.feedback.handler.CourseHandler;
import com.tict.project.feedback.handler.FacultyHandler;
import com.tict.project.feedback.handler.FeedbackHandler;
import com.tict.project.feedback.handler.LoginHandler;
import com.tict.project.feedback.handler.StudentHandler;
import com.tict.project.feedback.handler.SubjectHandler;
import com.tict.project.feedback.handler.UserHandler;
import com.tict.project.feedback.vo.Course;
import com.tict.project.feedback.vo.FeedbackConfigParam;
import com.tict.project.feedback.vo.Student;
import com.tict.project.feedback.vo.Subject;
import com.tict.project.feedback.vo.User;

/**
 * Servlet implementation class FeedbackServlet
 */
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminController adminController;
	private AuthController authController;
	private FacultyController facultyController;
	private StudentController studentController;
	
    /**
     * Default constructor. 
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public FeedbackServlet() throws ClassNotFoundException, IOException, SQLException {
    	adminController = new AdminController();
    	authController = new AuthController();
    	facultyController = new FacultyController();
    	studentController = new StudentController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String view = "";
		
		if("login".equalsIgnoreCase(action)) {
			view = authController.handleRequest(request, response);
		}
		else if("logout".equalsIgnoreCase(action)) {
			view = authController.handleRequest(request, response);
			response.sendRedirect(view);
			return;
		}
		else {			
			User user = getUserInfo(request);
			if(user == null) {
				view = FeedbackConsts.LOGIN_PAGE;
			}
			else {
				//valid user
				if(user.getType().equalsIgnoreCase("ADMIN")) {
					view = adminController.handleRequest(request, response);
				}
				else if(user.getType().equalsIgnoreCase("STUDENT")) {
					view = studentController.handleRequest(request, response);
				}
				else if(user.getType().equalsIgnoreCase("FACULTY")) {
					view = facultyController.handleRequest(request, response);
				}
			}
		}
		System.out.println("forwarding to view: "+view);
		request.getRequestDispatcher(view).forward(request, response);
	}

	private User getUserInfo(HttpServletRequest req) {
		return (User)req.getSession().getAttribute("user");
	}
	
	private void addErrorMsg(HttpServletRequest request, String msg) {
	request.setAttribute(FeedbackConsts.ERROR_MSG, msg);
	}
}


