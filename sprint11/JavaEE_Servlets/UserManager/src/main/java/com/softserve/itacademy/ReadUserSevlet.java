package com.softserve.itacademy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/read")
public class ReadUserSevlet extends HttpServlet {
       
	private UserDao userDao;
	
	@Override
	public void init() {
		userDao = UserDao.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = userDao.read(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/read-user.jsp").forward(request, response);
	}
}
