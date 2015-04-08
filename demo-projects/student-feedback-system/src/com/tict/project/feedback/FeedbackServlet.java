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

	private DatabaseConnector connector;
	private LoginHandler loginHandler;
	private UserHandler userHandler;
	private StudentHandler studentHandler;
	private CourseHandler courseHandler;
	private SubjectHandler subjectHandler;
	private FacultyHandler facultyHandler;
	private FeedbackHandler feedbackHandler;
	
	private AdminController adminController;
	
    /**
     * Default constructor. 
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public FeedbackServlet() throws ClassNotFoundException, IOException, SQLException {
    	connector = new DatabaseConnector();
    	loginHandler = new LoginHandler(connector);
    	userHandler = new UserHandler(connector);
    	studentHandler = new StudentHandler(connector);
    	courseHandler = new CourseHandler(connector);
    	subjectHandler = new SubjectHandler(connector);
    	facultyHandler = new FacultyHandler(connector);
    	feedbackHandler = new FeedbackHandler(connector);
    	//adminController = new AdminController();
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
		if("login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			boolean loginResult = loginHandler.login(username, password);
			if(loginResult) {
				User user = userHandler.getUser(username);
				request.getSession().setAttribute("user", user);
				if("admin".equalsIgnoreCase(user.getType())) {
					request.getRequestDispatcher("WEB-INF/Home.jsp").forward(request, response);
				}
				else if("student".equalsIgnoreCase(user.getType())) {
					request.getRequestDispatcher("WEB-INF/StudentHome.jsp").forward(request, response);
				}
				else if("faculty".equalsIgnoreCase(user.getType())) {
					request.getRequestDispatcher("WEB-INF/FacultyHome.jsp").forward(request, response);
				}
				else {
					addErrorMsg(request, "Invalid Credentials !");
					request.getRequestDispatcher("Error.jsp").forward(request, response);
				}
			}
			else {
				addErrorMsg(request, "Invalid Credentials !");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
		else if("logout".equalsIgnoreCase(action)) {
			request.getSession().invalidate();
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		else {			
			User user = getUserInfo(request);
			if(user == null) {
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
			else {
				//valid user
				/*if(user.getType().equalsIgnoreCase("ADMIN")) {
					String page = adminController.handleRequest(request, response);
					request.getRequestDispatcher(page).forward(request, response);
				}*/
				
				if("addStudent".equals(action)) {
					if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
						List<Course> courses = courseHandler.getAllCourse();
						request.setAttribute("courses", courses);
						request.getRequestDispatcher("WEB-INF/AddStudent.jsp").forward(request, response);
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
							request.getRequestDispatcher("WEB-INF/Home.jsp").forward(request, response);
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
							request.getRequestDispatcher("WEB-INF/AllUsers.jsp").forward(request, response);
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
						request.getRequestDispatcher("WEB-INF/UserInfo.jsp").forward(request, response);
					}
				}
				else if("editUser".equals(action)) {
					if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
						String uname = request.getParameter("uname");
						User viewedUser = userHandler.getUser(uname);
						request.setAttribute("user", viewedUser);
						request.getRequestDispatcher("WEB-INF/EditUser.jsp").forward(request, response);
					}
				}
				//Faculty level handling
				else if("listFaculties".equals(action)) {
					if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
						try {
							List<User> students = userHandler.listUsers(FeedbackConsts.ROLE_FACULTY);
							request.setAttribute("users", students);
							request.setAttribute("userType", "Faculties");
							request.getRequestDispatcher("WEB-INF/AllUsers.jsp").forward(request, response);
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
						request.getRequestDispatcher("WEB-INF/AddFaculty.jsp").forward(request, response);
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
							request.getRequestDispatcher("WEB-INF/Home.jsp").forward(request, response);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						/*try {
							studentHandler.saveStudent(student);
							request.setAttribute("msg", "Faculty Added Successfully.");
							request.getRequestDispatcher("WEB-INF/Home.jsp").forward(request, response);
						} catch (SQLException e) {
							e.printStackTrace();
						}*/
					}
				}
				
				
				//Feedback handling
				else if("addFeedback".equals(action)) {
					List<User> faculties = facultyHandler.getAllFaculties();
					request.setAttribute("faculties", faculties);
					List<Subject> subjects = subjectHandler.getAllSubjects();
					request.setAttribute("subjects", subjects);
					List<Course> courses = courseHandler.getAllCourse();
					request.setAttribute("courses", courses);
					request.getRequestDispatcher("WEB-INF/StudentFeedback.jsp").forward(request, response);
				}
				else if("saveFeedback".equals(action)) {
					try {
						feedbackHandler.saveFeedback(request, user.getId());
						request.getRequestDispatcher("WEB-INF/SuccessfulFeedback.jsp").forward(request, response);
					} catch (SQLException e) {
						e.printStackTrace();
						request.setAttribute("errorMsg", e.getMessage());
						request.getRequestDispatcher("Error.jsp").forward(request, response);
					}
					
				}
				else if("viewFeedbackForm".equals(action)) {
					List<Course> courses = courseHandler.getCourseForFaculty(user.getId());
					request.setAttribute("courses", courses);
					List<Subject> subjects = subjectHandler.getSubjectsForFaculty(user.getId());
					request.setAttribute("subjects", subjects);
					request.getRequestDispatcher("WEB-INF/ViewFeedback.jsp").forward(request, response);
				}
				else if("viewFeedback".equals(action)) {
					List<Course> courses = courseHandler.getCourseForFaculty(user.getId());
					request.setAttribute("courses", courses);
					List<Subject> subjects = subjectHandler.getSubjectsForFaculty(user.getId());
					request.setAttribute("subjects", subjects);
					
					List<FeedbackConfigParam> results = feedbackHandler.viewFeedback(user.getId());
					request.setAttribute("results", results);
					System.out.println(results);
					request.getRequestDispatcher("WEB-INF/ViewFeedback.jsp").forward(request, response);
				}
			}
		}
		
	}

	private User getUserInfo(HttpServletRequest req) {
		return (User)req.getSession().getAttribute("user");
	}
	
	private void addErrorMsg(HttpServletRequest request, String msg) {
		request.setAttribute(FeedbackConsts.ERROR_MSG, msg);
	}
}


