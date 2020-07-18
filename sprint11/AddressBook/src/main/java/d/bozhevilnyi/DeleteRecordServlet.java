package d.bozhevilnyi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/records/delete")
public class DeleteRecordServlet extends HttpServlet {
    
	private AddressBook addressBook;

    @Override
    public void init() {
        addressBook = AddressBook.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Address address = addressBook.getAddress(request.getParameter("firstName"), request.getParameter("lastName"));
        addressBook.delete(address.getFirstName(), address.getLastName());
        response.sendRedirect("/records/list");
    	
    }
}
