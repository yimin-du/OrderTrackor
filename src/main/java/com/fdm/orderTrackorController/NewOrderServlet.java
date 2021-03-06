package com.fdm.orderTrackorController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewOrderServlet extends HttpServlet{
	
	private static final long serialVersionUID = -4907484377162501694L;
	private PersistUtil persistUtil;

	public NewOrderServlet() {
		persistUtil = new PersistUtil();;
	}


	public NewOrderServlet(PersistUtil persistUtil) {
		this.persistUtil = persistUtil;
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("neworder.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		persistUtil.newOrder(req);
		RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
		dispatcher.forward(req, res);
			
	}
}
