package com.fdm.orderTrackorController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.orderTrackor.Customer;
import com.fdm.orderTrackor.CustomerService;

public class IndexServlet extends HttpServlet{

	private static final long serialVersionUID = -7566667038476922939L;
	private PersistUtil persistUtil;

	public IndexServlet() {
		persistUtil = new PersistUtil();;
	}


	public IndexServlet(PersistUtil persistUtil) {
		this.persistUtil = persistUtil;
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	// Validate registration data
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		CustomerService customerService = persistUtil.getCustomerService();
		Customer customer = customerService.findCustomerByUsername(username);
		if(customer == null) {
			customer = new Customer();
			customer.setUsername(req.getParameter("username"));
			customer.setPassword(req.getParameter("password"));
			customer.setName(req.getParameter("name"));
			customerService.persistCustomer(customer);
			req.setAttribute("regSucceed", true);
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.forward(req, res);

		} else {
			req.setAttribute("userExisted", true);
			RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
			dispatcher.forward(req, res);
		}		
	}

}
