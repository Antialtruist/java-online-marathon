package com.softserve.itacademy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {
       
	private UserDao userDao;
	
	@Override
	public void init() {
		userDao = UserDao.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/create-user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		userDao.create(user);
		response.sendRedirect("/users/list");
	}
}
