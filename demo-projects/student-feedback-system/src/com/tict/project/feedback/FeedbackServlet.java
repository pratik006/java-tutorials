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

import org.omg.CORBA.Request;

import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.handler.CourseHandler;
import com.tict.project.feedback.handler.FacultyHandler;
import com.tict.project.feedback.handler.LoginHandler;
import com.tict.project.feedback.handler.StudentHandler;
import com.tict.project.feedback.handler.SubjectHandler;
import com.tict.project.feedback.handler.UserHandler;
import com.tict.project.feedback.vo.Course;
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
				request.getRequestDispatcher("WEB-INF/Home.jsp").forward(request, response);				
			}
			else {
				request.setAttribute("errorMsg", "Invalid Credentials !");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
		else if("addStudent".equals(action)) {
			User user = getUserInfo(request);
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				List<Course> courses = courseHandler.getAllCourse();
				request.setAttribute("courses", courses);
				request.getRequestDispatcher("WEB-INF/AddStudent.jsp").forward(request, response);
			}
		}
		else if("saveStudent".equals(action)) {
			User user = getUserInfo(request);
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
			User user = getUserInfo(request);
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
			User user = getUserInfo(request);
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				String uname = request.getParameter("uname");
				User viewedUser = userHandler.getUser(uname);
				request.setAttribute("user", viewedUser);
				request.getRequestDispatcher("WEB-INF/UserInfo.jsp").forward(request, response);
			}
		}
		else if("editUser".equals(action)) {
			User user = getUserInfo(request);
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				String uname = request.getParameter("uname");
				User viewedUser = userHandler.getUser(uname);
				request.setAttribute("user", viewedUser);
				request.getRequestDispatcher("WEB-INF/EditUser.jsp").forward(request, response);
			}
		}
		//Faculty level handling
		else if("listFaculties".equals(action)) {
			User user = getUserInfo(request);
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
			User user = getUserInfo(request);
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				List<Subject> subjects = subjectHandler.getAllSubjects();
				request.setAttribute("subjects", subjects);
				List<Course> courses = courseHandler.getAllCourse();
				request.setAttribute("courses", courses);
				request.getRequestDispatcher("WEB-INF/AddFaculty.jsp").forward(request, response);
			}
		}
		else if("saveFaculty".equals(action)) {
			User user = getUserInfo(request);
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
	}

	private User getUserInfo(HttpServletRequest req) {
		return (User)req.getSession().getAttribute("user");
	}
}


