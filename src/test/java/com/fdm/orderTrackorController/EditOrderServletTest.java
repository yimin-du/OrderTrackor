package com.fdm.orderTrackorController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.*;

import com.fdm.orderTrackor.Customer;
import com.fdm.orderTrackor.Order;
import com.fdm.orderTrackor.OrderService;

import static org.mockito.Mockito.*;

import java.io.IOException;

public class EditOrderServletTest {
	private final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
	private final HttpServletRequest request = mock(HttpServletRequest.class);
	private final HttpServletResponse response = mock(HttpServletResponse.class);
	private final HttpSession session = mock(HttpSession.class);
	private final Order order = mock(Order.class);
	private final OrderService orderService = mock(OrderService.class);
	private final PersistUtil persistUtil = mock(PersistUtil.class);
	private final EditOrderServlet servlet = new EditOrderServlet(persistUtil);
	private Customer customer = mock(Customer.class);

	
	@Test
	public void do_get_should_forward_to_editorder_jsp() throws ServletException, IOException {
		when(request.getParameter(any())).thenReturn("1");
		when(persistUtil.getOrderService()).thenReturn(orderService);
		when(orderService.findOrderByID(any())).thenReturn(order);
		when(request.getRequestDispatcher(any())).thenReturn(dispatcher);
		servlet.doGet(request, response);
		verify(request).setAttribute("order", order);
		verify(dispatcher).forward(request, response);
		
	}
	
	@Test
	public void do_post_should_forward_to_home_jsp() throws ServletException, IOException {
		when(request.getParameter(any())).thenReturn("1");
		when(persistUtil.getOrderService()).thenReturn(orderService);
		when(orderService.findOrderByID(any())).thenReturn(order);
		when(order.getSender()).thenReturn(customer);
		when(request.getSession()).thenReturn(session);
		when(request.getRequestDispatcher(any())).thenReturn(dispatcher);

		servlet.doPost(request, response);
		verify(session).setAttribute("loginUser", customer);
		verify(dispatcher).forward(request, response);
	}
}
