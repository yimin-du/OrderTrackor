package com.fdm.orderTrackorController;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import com.fdm.orderTrackor.Courier;
import com.fdm.orderTrackor.CourierService;
import com.fdm.orderTrackor.Customer;
import com.fdm.orderTrackor.CustomerService;
import com.fdm.orderTrackor.EmployeeService;
import com.fdm.orderTrackor.Order;
import com.fdm.orderTrackor.OrderService;
import com.fdm.orderTrackor.OrderStatus;

public class PersistUtil {
	
	private CustomerService customerService;
	private OrderService orderService;
	private EntityManagerFactory emf;
	private CourierService courierService;
	private EmployeeService employeeService;
	
	public PersistUtil() {
		emf = Persistence.createEntityManagerFactory("order");
		customerService = new CustomerService(emf);
		orderService = new OrderService(emf);
		courierService = new CourierService(emf);
		employeeService = new EmployeeService(emf);
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public CourierService getCourierService() {
		return courierService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public boolean checkLogin(String username, String password) {
		Customer retrievedCustomer = customerService.findCustomerByUsername(username);
		if(retrievedCustomer != null && retrievedCustomer.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
		
	}

	public void newOrder(HttpServletRequest req) {
		String receiverName = req.getParameter("receivername");
		String receiverAddress = req.getParameter("receiveraddress");
		Order order = new Order();
		Customer customer = (Customer) req.getSession().getAttribute("loginUser");
		Courier courier = this.getCourierService().findAvailableCourier();
		order.setSender(customer);
		order.setCost(10);
		order.setCourier(courier);
		Date date = new Date();
		order.setEstimateDeliveryDate(addDays(date, 3));
		order.setOrderDate(date);
		order.setReceiverAddress(receiverAddress);
		order.setReceiverName(receiverName);
		order.setStatus(OrderStatus.DISPATCHED);
		this.getOrderService().persistOrder(order);
		this.getCustomerService().addOrder(customer, order);
	}
	
	public Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}

	public void updateOrder(HttpServletRequest req) {
		OrderService orderService = getOrderService();
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		Order order = orderService.findOrderByID(orderId);
		Order newOrder = new Order();
		newOrder.setOrderId(orderId);
		newOrder.setReceiverName(req.getParameter("receivername"));
		newOrder.setReceiverAddress(req.getParameter("receiveraddress"));
		orderService.updateOrder(newOrder);
	}

	public Customer getLoginUser() {
		
		return null;
	}

	
}
