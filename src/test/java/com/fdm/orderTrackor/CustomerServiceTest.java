package com.fdm.orderTrackor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerServiceTest {
	private static CustomerService customerService;
	private static OrderService orderService;
	private Order order;
	private Customer customer;
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.configure();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
		customerService = new CustomerService(emf);
		orderService = new OrderService(emf);
	}
	
	@Before
	public void setup() {
		customer = new Customer();
		customer.setName("alex");
		customer.setUsername("alex001");
		customer.setPassword("123");		
		customerService.persistCustomer(customer);
		
				
		order = new Order();
		order.setSender(customer);
		order.setReceiverAddress("1 glenelg SA 5010");
		order.setStatus(OrderStatus.PROCESSING);	
		orderService.persistOrder(order);
		
	}
	
	@Test
	public void persistCustomer_insert_new_customer_in_db() {
		Customer retrievedCustomer = customerService.findCustomerByID(customer.getCustomerId());
		assertEquals(customer, retrievedCustomer);
	}
	
	
	@Test
	public void test_findCustomerByCustomerUsername() {
		String username = customer.getUsername();
		Customer retrievedCustomer = customerService.findCustomerByUsername(username);
		assertEquals(customer, retrievedCustomer);

	}
	
	
	@Test
	public void test_removeCustomer() {
		customerService.removeCustomer(customer.getCustomerId());
		Assert.assertNull(customerService.findCustomerByID(customer.getCustomerId()));
	}
	
	@Test
	public void test_addOrder() {
		Order order2 = new Order();
		order2.setSender(customer);
		order2.setReceiverAddress("1 OConnel ST, Sydney NSW 2000");
		order2.setStatus(OrderStatus.PROCESSING);	
		orderService.persistOrder(order2);
		
		Order order3 = new Order();
		order3.setSender(customer);
		order3.setReceiverAddress("10 Beach RD Randwick NSW 2030");
		order3.setStatus(OrderStatus.PROCESSING);	
		orderService.persistOrder(order3);

		customerService.addOrder(customer, order2);
		customerService.addOrder(customer, order3);
		Customer retrievedCustomer = customerService.findCustomerByID(customer.getCustomerId());
		Assert.assertTrue(retrievedCustomer.getOrders().contains(order2));
		Assert.assertTrue(retrievedCustomer.getOrders().contains(order3));
	}
	
	
	@Test
	public void test_removeOrder() {
		Order order4 = new Order();
		order4.setSender(customer);
		order4.setReceiverAddress("10 Beach RD Randwick NSW 2030");
		order4.setStatus(OrderStatus.PROCESSING);	
		orderService.persistOrder(order4);
		
		customerService.removeOrder(customer, order4);
		Customer retrievedCustomer = customerService.findCustomerByID(customer.getCustomerId());
		Assert.assertFalse(retrievedCustomer.getOrders().contains(order4));

	}

}
