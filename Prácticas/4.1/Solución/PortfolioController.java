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
 * Servlet implementation class PortfolioController
 */
@WebServlet("/PortfolioController")
public class PortfolioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PortfolioController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerId = request.getParameter("customerIdentity");

        BrokerModel model = BrokerModelImpl.getInstance();

        try {
            CustomerShare[] shares = model.getAllCustomerShares(customerId);
            Customer customer = model.getCustomer(customerId);
            request.setAttribute("shares", shares);
            request.setAttribute("customer", customer);
        } catch (BrokerException be) {
            request.setAttribute("message", be.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Portfolio.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
