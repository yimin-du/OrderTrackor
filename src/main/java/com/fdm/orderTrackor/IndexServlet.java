package com.fdm.orderTrackor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet{

	private static final long serialVersionUID = -7566667038476922939L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	// Validate registration data
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		CustomerService customerService = PersistUtil.getCustomerService();
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
