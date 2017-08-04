package trader.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trader.*;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController", "/AllCustomers"})
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

		BrokerModel model = BrokerModelImpl.getInstance();
		
		String path = request.getServletPath();
		if (path.equals("/CustomerController")) {
		
		    String id = request.getParameter("customerIdentity");
		    String name = request.getParameter("customerName");
		    String address = request.getParameter("customerAddress");
		    String submit = request.getParameter("submit");
		
		    try {
		
		        if (submit.equals("Get Customer")) {
		            Customer cust = model.getCustomer(id);
		            request.setAttribute("customer", cust);
		        } else if (submit.equals("Update Customer")) {
		            model.updateCustomer(new Customer(id, name, address));
		            Customer cust = model.getCustomer(id);
		            request.setAttribute("customer", cust);
		        } else if (submit.equals("Add Customer")) {
		            model.addCustomer(new Customer(id, name, address));
		            Customer cust = model.getCustomer(id);
		            request.setAttribute("customer", cust);
		        } else if (submit.equals("Delete Customer")) {
		            model.deleteCustomer(new Customer(id, name, address));
		        }
		    } catch (BrokerException be) {
		        request.setAttribute("message", be.getMessage());
		    }
		
		    RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerDetails");
		    dispatcher.forward(request, response);
		
		} else if (path.equals("/AllCustomers")) {
		
		    try {
		
		        Customer[] customers = model.getAllCustomers();
		        request.setAttribute("customers", customers);
		
		    } catch (BrokerException be) {
		        request.setAttribute("message", be.getMessage());
		    }
		
		    RequestDispatcher dispatcher = request.getRequestDispatcher("AllCustomers.jsp");
		    dispatcher.forward(request, response);
		
		}
	}

    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
