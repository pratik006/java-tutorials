package com.prapps.hello;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prapps.student.api.HelloWorldBeanRemote;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB
	HelloWorldBeanRemote helloWorldBeanRemote;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String action = req.getParameter("action");
		System.out.println("action: "+action);
		if("remote".equals(action)) {
			try {
				resp.getOutputStream().write(helloWorldBeanRemote.searchStudents(null).get(0).getFirstName().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
