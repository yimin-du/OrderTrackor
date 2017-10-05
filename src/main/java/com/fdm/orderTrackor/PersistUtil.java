package com.fdm.orderTrackor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	
}
