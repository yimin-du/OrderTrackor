package com.fdm.orderTrackor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CustomerService {
	
	private EntityManagerFactory emf;
	private OrderService orderService;

	public CustomerService(EntityManagerFactory emf) {
		this.emf = emf;
		this.orderService = new OrderService(emf);
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void persistCustomer(Customer customer) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(customer);
		et.commit();
		em.close();
	}
	
	public void removeCustomer(Long id) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		Customer customer = em.find(Customer.class, id);
		try {
			if (customer != null) {
				et.begin();
				em.remove(customer);
				for(Order order : customer.getOrders()) {
					orderService.removeOrder(order.getOrderId());
				}
				et.commit();
			}
		} finally {
			em.close();
		}
	}
	
	public Customer findCustomerByID(Long id) {
		Customer customer = new Customer();
		EntityManager em = getEntityManager();
		try {
			customer = em.find(Customer.class, id);
		} finally {
			em.close();
		}
		return customer;
	}
	
	public Customer findCustomerByUsername(String username) {
		TypedQuery<Customer> query =
				getEntityManager().createQuery("SELECT c from Customer c "
						+ "WHERE c.username = :username", Customer.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}
	
	public void addOrder(Customer customer, Order order) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		customer.addOrder(order);
		et.commit();
		em.close();
	}
	
	public void removeOrder(Customer customer, Order order) {
		customer.removeOrder(order);
		orderService.removeOrder(order.getOrderId());
	}

}
