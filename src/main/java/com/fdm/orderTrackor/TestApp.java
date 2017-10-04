package com.fdm.orderTrackor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;

public class TestApp {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
		OrderService orderService = new OrderService(emf);
		CustomerService customerService = new CustomerService(emf);
		
		
		customerService.removeCustomer(4L);
		
	}

}
