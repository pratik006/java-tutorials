package com.tict.project.feedback.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tict.project.feedback.vo.Course;
import com.tict.project.feedback.vo.FeedbackConfigParam;
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
			/*List<User> faculties = facultyHandler.getAllFaculties();
			request.setAttribute("faculties", faculties);
			List<Subject> subjects = subjectHandler.getAllSubjects();
			request.setAttribute("subjects", subjects);
			List<Course> courses = courseHandler.getAllCourse();
			request.setAttribute("courses", courses);*/
			List<FeedbackConfigParam> feedbackParams = feedbackHandler.getConfigParams();
			request.setAttribute("params", feedbackParams);
			
			try {
				String subjectId = request.getParameter("subjectId");
				String facultyId = request.getParameter("facultyId");
				System.out.println("subjectID: "+subjectId);
				Map map = feedbackHandler.getFeedbackSubjects(user.getId(), subjectId, facultyId);
				request.setAttribute("courses", map.get("courses"));
				request.setAttribute("subjects", map.get("subjects"));
				request.setAttribute("faculties", map.get("faculties"));
				view = "WEB-INF/StudentFeedback.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				view = "Error.jsp";
			}
			
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
		else if("getFacForSub".equals(action)) {
			long subjectId = Integer.parseInt(request.getParameter("subjectId"));
			User faculty;
			try {
				faculty = facultyHandler.getFaculty(subjectId, user.getId());
				request.setAttribute("faculty", faculty);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if("changePasswordView".equals(action)) {
			view = changePassword();
		}
		else if("changePassword".equals(action)) {
			String oldPassword = request.getParameter("currentPassword");
			String newPassword = request.getParameter("newPassword");
			String confirmNewPassword = request.getParameter("confirmNewPassword");
			if(newPassword.equals(confirmNewPassword)) {
				try {
					boolean resp = userHandler.changePassword(user.getUsername(), oldPassword, newPassword);
					if(resp) {
						addMsg(request, "Password successfully changes.");
						view = "WEB-INF/Home.jsp";
					}
					else {
						addMsg(request, "Invalid Credentials");
						view = "WEB-INF/Home.jsp";
					}
				} catch (SQLException e) {
					e.printStackTrace();
					addErrorMsg(request, "Error Occurred.");
					view = "WEB-INF/Home.jsp";
				}
			}
			else {
				addErrorMsg(request, "Your passwords donot match");
				view = "WEB-INF/ChangePassword.jsp";
			}
		}
		else {
			view = handleDefaultRequest(request, response);
		}
		if(view == null) {
			System.err.println("StudentController -> Cannot handle this request. action: "+action);
		}
		return view;
	}
}
