package com.fdm.orderTrackorController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.orderTrackor.Customer;

public class HomeServlet extends HttpServlet{

	private static final long serialVersionUID = 353156617168003618L;

	private PersistUtil persistUtil;

	public HomeServlet() {
		persistUtil = new PersistUtil();;
	}


	public HomeServlet(PersistUtil persistUtil) {
		this.persistUtil = persistUtil;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
		Customer customer = persistUtil.getLoginUser();

		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();

		if(persistUtil.checkLogin(username, password)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
			Customer customer = persistUtil.getCustomerService().findCustomerByUsername(username);
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
