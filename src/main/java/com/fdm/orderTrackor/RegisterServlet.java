package com.fdm.orderTrackor;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1259499463162791268L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
		dispatcher.forward(req, res);
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CustomerService customerService = PersistUtil.getCustomerService();
		Customer customer = new Customer();
		customer.setUsername(req.getParameter("username"));
		customer.setPassword(req.getParameter("password"));
		customer.setName(req.getParameter("name"));

		customerService.persistCustomer(customer);
	}

}
