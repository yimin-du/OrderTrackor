package com.fdm.orderTrackor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 353156617168003618L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();

		if(PersistUtil.checkLogin(username, password)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
			Customer customer = PersistUtil.getCustomerService().findCustomerByUsername(username);
			session.setAttribute("loginUser", customer );
			dispatcher.forward(req, res);
		} else {
			req.setAttribute("loginFail", true);
			req.setAttribute("username", username);
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.forward(req, res);
		}

		
	}

}
