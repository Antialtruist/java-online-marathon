package d.bozhevilnyi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/records/create")
public class CreateRecordServlet extends HttpServlet {
       
	private AddressBook addressBook;
	private String message = "";
	
	@Override
	public void init() {
		addressBook = AddressBook.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/create-record.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		
		if (!addressBook.create(firstName, lastName, address)) {
			message = "An error occurred! Please try again!";
	        request.setAttribute("message", message);
	        message = "";
	        request.getRequestDispatcher("/WEB-INF/create-record.jsp").forward(request, response);
		} else {
			response.sendRedirect("/records/list");
		}
	}
}