package com.fdm.orderTrackor;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CourierServiceTest {
	private static CourierService courierService;
	private static OrderService orderService;
	private static CustomerService customerService;
	private Order order;
	private Courier courier;
	private Customer customer;
	
	@BeforeClass
	public static void setupClass() {
		BasicConfigurator.configure();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
		courierService = new CourierService(emf);
		orderService = new OrderService(emf);
		customerService = new CustomerService(emf);
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
		
		courier = new Courier();
		courier.setCurrentLocation("Hyde Park");
		courier.setStatus(CourierStatus.WORKING);
		courier.addOrder(order);
		
		courierService.persistCourier(courier);

		
	}
	
	@Test
	public void persistCourier_insert_new_courier_in_db() {
		Courier retrievedCourier = courierService.findCourierByID(courier.getCourierId());
		assertEquals(courier, retrievedCourier);
	}
	
	
	@Test
	public void test_removeCourier() {
		courierService.removeCourier(courier.getCourierId());
		Assert.assertNull(courierService.findCourierByID(courier.getCourierId()));
	}
	
	
}
