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
	
	private static CustomerService customerService;
	private static OrderService orderService;
	private static EntityManagerFactory emf;
	private static CourierService courierService;
	private static EmployeeService employeeService;
	
	static {
		emf = Persistence.createEntityManagerFactory("order");
		customerService = new CustomerService(emf);
		orderService = new OrderService(emf);
		courierService = new CourierService(emf);
		employeeService = new EmployeeService(emf);
	}

	public static CustomerService getCustomerService() {
		return customerService;
	}

	public static OrderService getOrderService() {
		return orderService;
	}

	public static CourierService getCourierService() {
		return courierService;
	}

	public static EmployeeService getEmployeeService() {
		return employeeService;
	}

	public static boolean checkLogin(String username, String password) {
		Customer retrievedCustomer = customerService.findCustomerByUsername(username);
		if(retrievedCustomer != null && retrievedCustomer.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
		
	}

	public static void newOrder(HttpServletRequest req) {
		String receiverName = req.getParameter("receivername");
		String receiverAddress = req.getParameter("receiveraddress");
		Order order = new Order();
		Customer customer = (Customer) req.getSession().getAttribute("loginUser");
		Courier courier = PersistUtil.getCourierService().findAvailableCourier();
		order.setSender(customer);
		order.setCost(10);
		order.setCourier(courier);
		Date date = new Date();
		order.setEstimateDeliveryDate(addDays(date, 3));
		order.setOrderDate(date);
		order.setReceiverAddress(receiverAddress);
		order.setReceiverName(receiverName);
		order.setStatus(OrderStatus.DISPATCHED);
		PersistUtil.getOrderService().persistOrder(order);
		PersistUtil.getCustomerService().addOrder(customer, order);
	}
	
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}

	
}