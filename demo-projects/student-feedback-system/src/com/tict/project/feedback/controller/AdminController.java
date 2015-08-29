package com.tict.project.feedback.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.tict.project.feedback.consts.FeedbackConsts;
import com.tict.project.feedback.vo.Course;
import com.tict.project.feedback.vo.Faculty;
import com.tict.project.feedback.vo.SemesterSubject;
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
		else if("saveUser".equals(action)) {
			if(user != null && FeedbackConsts.ROLE_ADMIN.equals(user.getType())) {
				Student student = new Student();
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String type = request.getParameter("utype");
				String username = request.getParameter("username");
				
				student.setFirstName(fname);
				student.setLastName(lname);
				student.setType(type);
				student.setUsername(username);
				try {
					userHandler.saveUser(student);
					request.setAttribute("msg", "User updated Successfully.");
					view = "WEB-INF/Home.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
				Faculty faculty = new Faculty();
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String type = request.getParameter("utype");
				String username = request.getParameter("username");
				String gender = request.getParameter("gender");
				String email = request.getParameter("email");
				faculty.setFirstName(fname);
				faculty.setLastName(lname);
				faculty.setType(type);
				faculty.setUsername(username);
				if(gender != null && gender.length() > 0) {
					faculty.setGender(gender);
				}
				if(email != null && email.length() > 0) {
					faculty.setEmail(email);
				}
				System.out.println(Arrays.toString(request.getParameterValues("courseId")));
				System.out.println(Arrays.toString(request.getParameterValues("semester")));
				System.out.println(Arrays.toString(request.getParameterValues("subject")));
				List<String[]> subjects = new ArrayList<String[]>();
				List<SemesterSubject> semesterSubjects = new ArrayList<SemesterSubject>();
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
								
								SemesterSubject ss = new SemesterSubject();
								ss.setSemesterId(Integer.parseInt(str[1]));
								ss.setSubjectId(Integer.parseInt(str[2]));
								semesterSubjects.add(ss);
							}
						}
					}					
				}
				try {
					faculty.setSemesterSubjects(semesterSubjects);
					facultyHandler.addFaculty(faculty, subjects);
					request.setAttribute("msg", "Faculty Added Successfully.");
					view = "WEB-INF/Home.jsp";
				} catch (MySQLIntegrityConstraintViolationException e1) {
					e1.printStackTrace();
					request.setAttribute(FeedbackConsts.ERROR_MSG, "Entry already existing with same name. Cannot add Faculty");
					view = "WEB-INF/Home.jsp";
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if("viewFeedback".equals(action)) {
			try {
				String[][] arr = feedbackHandler.viewFeedback();
				request.setAttribute("results", arr);
				view = "WEB-INF/ViewFeedback.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if("addStudentsPage".equals(action)) {
			view = "WEB-INF/AddStudents.jsp";
		}		
		else if("uploadStudents".equals(action)) {
			if( ServletFileUpload.isMultipartContent(request)) {
				try{ 
					// Create a factory for disk-based file items
					DiskFileItemFactory factory = new DiskFileItemFactory();

					// Configure a repository (to ensure a secure temp location is used)
					/*ServletContext servletContext = request.getServletConfig().getServletContext();
					File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
					factory.setRepository(repository);*/

					// Create a new file upload handler
					ServletFileUpload upload = new ServletFileUpload(factory);
				      // Parse the request to get file items.
				      List fileItems = upload.parseRequest(request);
					
				      // Process the uploaded file items
				      Iterator i = fileItems.iterator();

				      while ( i.hasNext () ) 
				      {
				         FileItem fi = (FileItem)i.next();
				         if ( !fi.isFormField () )	
				         {
				            // Get the uploaded file parameters
				            BufferedReader reader = new BufferedReader(new InputStreamReader(fi.getInputStream()));
				            String line = null;
				            StringBuilder query = new StringBuilder();
				            reader.readLine();
				            while((line = reader.readLine()) != null) {
				            	String attrs[] = line.split(",");
				            	query.append("insert into "+FeedbackConsts.SCHEMA+".USER(ID, UNAME, UTYPE, FNAME, LNAME, GENDER, CASTE, NATIONALITY, DOB, EMAIL) VALUES(");
				            			query.append(attrs[1]+", ");
				            			query.append(attrs[1]+", ");
				            			query.append("'STUDENT', ");
				            			query.append("'"+attrs[2]+"', ");
				            			query.append("'"+attrs[3]+"', ");
				            			query.append("'"+attrs[4]+"', ");
				            			query.append("'"+attrs[5]+"', ");
				            			query.append("'"+attrs[6]+"', ");
				            			query.append("STR_TO_DATE('"+attrs[7]+"', '%d/%m/%Y'), ");
				            			query.append("'"+attrs[8]+"'");
				            			query.append(");");
				            }
				            System.out.println("query: "+query);
				            int updatedRowCount = connector.executeUpdate(query.toString());
				            if(updatedRowCount > 1) {
				            	request.setAttribute(FeedbackConsts.MSG, "Students added successfully.");
				            }
				         }
				      }
				      
				      view = "WEB-INF/Home.jsp";
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		else {
			System.out.println("cannot handle action: "+action+", fallling to default view");
			view = handleDefaultRequest(request, response);
		}
		return view;
	}
}
