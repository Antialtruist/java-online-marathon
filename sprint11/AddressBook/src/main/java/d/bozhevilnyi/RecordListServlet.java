package d.bozhevilnyi;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/records/list")
public class RecordListServlet extends HttpServlet {
       
	private AddressBook addressBook;
	
	@Override
	public void init() {
		addressBook = AddressBook.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/record-list.jsp");
        request.setAttribute("addresses", addressBook.readAll());
        requestDispatcher.forward(request, response);
	}
}