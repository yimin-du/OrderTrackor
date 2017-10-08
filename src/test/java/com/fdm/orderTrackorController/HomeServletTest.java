package com.fdm.orderTrackorController;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.fdm.orderTrackor.Customer;
import com.fdm.orderTrackor.CustomerService;
import com.fdm.orderTrackor.Order;
import com.fdm.orderTrackor.OrderService;

public class HomeServletTest {
	private final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
	private final HttpServletRequest request = mock(HttpServletRequest.class);
	private final HttpServletResponse response = mock(HttpServletResponse.class);
	private final HttpSession session = mock(HttpSession.class);
	private final Order order = mock(Order.class);
	private final CustomerService customerService = mock(CustomerService.class);
	private final PersistUtil persistUtil = mock(PersistUtil.class);
	private final HomeServlet servlet = new HomeServlet(persistUtil);
	private Customer customer = mock(Customer.class);

	
	@Test
	public void do_get_should_forward_to_editorder_jsp() throws ServletException, IOException {
		when(request.getRequestDispatcher(any())).thenReturn(dispatcher);
		when(persistUtil.getLoginUser()).thenReturn(customer);
		servlet.doGet(request, response);
		verify(dispatcher).forward(request, response);		
	}
	
	@Test
	public void do_post_should_forward_to_home_jsp() throws ServletException, IOException {
		when(request.getSession()).thenReturn(session);
		when(persistUtil.checkLogin(any(), any())).thenReturn(true);
		when(request.getRequestDispatcher(any())).thenReturn(dispatcher);
		
		when(persistUtil.getCustomerService()).thenReturn(customerService);
		when(customerService.findCustomerByUsername(any())).thenReturn(customer);
		when(order.getSender()).thenReturn(customer);
		when(request.getSession()).thenReturn(session);

		servlet.doPost(request, response);
		verify(session).setAttribute("loginUser", customer);
		verify(dispatcher).forward(request, response);
	}
}
