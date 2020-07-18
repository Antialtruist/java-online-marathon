package d.bozhevilnyi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/records/update")
public class UpdateRecordServlet extends HttpServlet {
    
	private AddressBook addressBook;
	private Address address;

    @Override
    public void init() {
        addressBook = AddressBook.getInstance();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	address = addressBook.getAddress(request.getParameter("firstName"), request.getParameter("lastName"));
        if (address == null) {
            response.sendError(404);
        } else {
            request.setAttribute("address", address);
            request.getRequestDispatcher("/WEB-INF/update-record.jsp").forward(request, response);
        }
    	
    	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	address.setAddress(request.getParameter("address"));
        addressBook.update(address.getFirstName(), address.getLastName(), address.getAddress());
        response.sendRedirect("/records/list");
    	
    }
}
