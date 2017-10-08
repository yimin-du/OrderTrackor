package com.fdm.orderTrackorController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.orderTrackor.Customer;
import com.fdm.orderTrackor.Order;

public class EditOrderServlet extends HttpServlet{

	private static final long serialVersionUID = -2626772973296961317L;
	private PersistUtil persistUtil;
	
	public EditOrderServlet() {
		persistUtil = new PersistUtil();;
	}
	
	
	public EditOrderServlet(PersistUtil persistUtil) {
		this.persistUtil = persistUtil;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		Order order = persistUtil.getOrderService().findOrderByID(orderId);

		req.setAttribute("order", order);
		RequestDispatcher dispatcher = req.getRequestDispatcher("editorder.jsp");
		dispatcher.forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		persistUtil.updateOrder(req);
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		Order order = persistUtil.getOrderService().findOrderByID(orderId);
		Customer customer = order.getSender();
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", customer);
		RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
		dispatcher.forward(req, res);
		
	}


}
