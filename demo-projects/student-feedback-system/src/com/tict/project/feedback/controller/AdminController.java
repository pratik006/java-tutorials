package com.tict.project.feedback.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.vo.Course;
import com.tict.project.feedback.vo.Student;
import com.tict.project.feedback.vo.Subject;
import com.tict.project.feedback.vo.User;

public class AdminController extends AbstractController {

	public AdminController() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String action = getAction(request);
		User user = getUserInfo(request);
		String view = null;
		
		if("addStudent".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				List<Course> courses = courseHandler.getAllCourse();
				request.setAttribute("courses", courses);
				view = "WEB-INF/AddStudent.jsp";
			}
		}
		else if("saveStudent".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				Student student = new Student();
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String type = request.getParameter("utype");
				String username = request.getParameter("username");
				String rollNumber = request.getParameter("rollNumber");
				String semester = request.getParameter("semester");
				long courseId = Long.parseLong(request.getParameter("courseId"));
				student.setFirstName(fname);
				student.setLastName(lname);
				student.setType(type);
				student.setUsername(username);
				student.setRollNumber(rollNumber);
				student.setSemester(semester);
				student.setCourseId(courseId);
				try {
					studentHandler.saveStudent(student);
					request.setAttribute("msg", "Student Added Successfully.");
					view = "WEB-INF/Home.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if("listStudents".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				try {
					List<User> students = userHandler.listUsers(FeedbackConsts.ROLE_STUDENT);
					request.setAttribute("users", students);
					request.setAttribute("userType", "Students");
					view = "WEB-INF/AllUsers.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if("viewUser".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				String uname = request.getParameter("uname");
				User viewedUser = userHandler.getUser(uname);
				request.setAttribute("user", viewedUser);
				view = "WEB-INF/UserInfo.jsp";
			}
		}
		else if("editUser".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				String uname = request.getParameter("uname");
				User viewedUser = userHandler.getUser(uname);
				request.setAttribute("user", viewedUser);
				view = "WEB-INF/EditUser.jsp";
			}
		}
		//Faculty level handling
		else if("listFaculties".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				try {
					List<User> students = userHandler.listUsers(FeedbackConsts.ROLE_FACULTY);
					request.setAttribute("users", students);
					request.setAttribute("userType", "Faculties");
					view = "WEB-INF/AllUsers.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if("addFaculty".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				List<Subject> subjects = subjectHandler.getAllSubjects();
				request.setAttribute("subjects", subjects);
				List<Course> courses = courseHandler.getAllCourse();
				request.setAttribute("courses", courses);
				view = "WEB-INF/AddFaculty.jsp";
			}
		}
		else if("saveFaculty".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				User faculty = new User();
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String type = request.getParameter("utype");
				String username = request.getParameter("username");
				faculty.setFirstName(fname);
				faculty.setLastName(lname);
				faculty.setType(type);
				faculty.setUsername(username);
				System.out.println(Arrays.toString(request.getParameterValues("courseId")));
				System.out.println(Arrays.toString(request.getParameterValues("semester")));
				System.out.println(Arrays.toString(request.getParameterValues("subject")));
				List<String[]> subjects = new ArrayList<String[]>();
				for(int i=0;i<request.getParameterValues("courseId").length;i++) {
					if(request.getParameterValues("courseId")[i] != null && request.getParameterValues("courseId")[i].length() > 0) {
						if(request.getParameterValues("semester")[i] != null && request.getParameterValues("semester")[i].length() > 0) {
							if(request.getParameterValues("subject")[i] != null && request.getParameterValues("subject")[i].length() > 0) {
								String[] str = new String[3];
								str[0] = request.getParameterValues("courseId")[i];
								str[1] = request.getParameterValues("semester")[i];
								str[2] = request.getParameterValues("subject")[i];
								System.out.println(str[0]+", "+str[1]+", "+str[2]);
								subjects.add(str);
							}
						}
					}					
				}
				try {
					facultyHandler.addFaculty(faculty, subjects);
					request.setAttribute("msg", "Faculty Added Successfully.");
					view = "WEB-INF/Home.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return view;
	}
}
