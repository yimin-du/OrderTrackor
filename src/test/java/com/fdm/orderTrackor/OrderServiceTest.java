package com.fdm.orderTrackor;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;
import org.junit.*;


public class OrderServiceTest {

	private static OrderService orderService;
	private static CustomerService customerService;
	private Order order, order2;
	private Customer sender, sender2;
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.configure();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
		orderService = new OrderService(emf);
		customerService = new CustomerService(emf);
	}
	
	@Before
	public void setup() {
		sender = new Customer();
		sender.setName("alex");
		sender.setUsername("alex001");
		sender.setPassword("123");		
		customerService.persistCustomer(sender);
		
		sender2 = new Customer();
		sender2.setName("molly");
		sender2.setUsername("molly001");
		sender2.setPassword("321");		
		customerService.persistCustomer(sender2);
		
		order = new Order();
		order.setSender(sender);
		order.setReceiverAddress("1 glenelg SA 5010");
		order.setStatus(OrderStatus.PROCESSING);	
		
		order2 = new Order();
		order2.setSender(sender2);
		order2.setReceiverAddress("88 Adelaide SA 5000");
		order2.setStatus(OrderStatus.PROCESSING);	

	}
	
	@Test
	public void persistOrder_insert_new_order_in_db() {
		orderService.persistOrder(order);
		Order retrievedOrder = orderService.findOrderByID(order.getOrderId());
		assertEquals(order, retrievedOrder);
	}
	
	
	@Test
	public void test_findOrderByCustomerUsername() {
		orderService.persistOrder(order);
		String username = order.getSender().getUsername();
		List<Order> retrievedOrders = orderService.findOrdersByCustomerUsername(username);
		assertTrue(retrievedOrders.contains(order));
	}
	
	
	@Test
	public void test_removeOrder() {
		orderService.persistOrder(order2);
		Order retrievedOrder = orderService.findOrderByID(order2.getOrderId());
		assertEquals(order2, retrievedOrder);
		orderService.removeOrder(order2.getOrderId());
		Assert.assertNull(orderService.findOrderByID(order2.getOrderId()));
	}
	
	
	}
