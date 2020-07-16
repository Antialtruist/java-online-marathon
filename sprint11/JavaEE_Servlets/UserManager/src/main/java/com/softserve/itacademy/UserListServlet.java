package com.softserve.itacademy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/list")
public class UserListServlet extends HttpServlet {
       
	private UserDao userDao;
	
	@Override
	public void init() {
		userDao = UserDao.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user-list.jsp");
		request.setAttribute("users", userDao.readAll());
		requestDispatcher.forward(request, response);
	}
}
