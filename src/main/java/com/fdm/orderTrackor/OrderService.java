package com.fdm.orderTrackor;

import java.util.List;

import javax.persistence.*;

public class OrderService {

	private EntityManagerFactory emf;

	public OrderService(EntityManagerFactory emf) {
		this.emf = emf;
	}

	// Best practise is to use a new EntityManager per transaction,
	// so create a method to get one.
	// Compare this to a 'getConnection()' method in JDBC
	// Don't forget to close this once your transaction is complete!

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Write a Order to the database
	public void persistOrder(Order order) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(order);
		et.commit();
		em.close();
	}
	
	
	


	// Remove a Order from the database if they exist
	public void removeOrder(Long id) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		Order order = em.find(Order.class, id);
		try {
			if (order != null) {
				et.begin();
				em.remove(order);
				et.commit();
			}
		} finally {
			em.close();
		}
	}


	public Order findOrderByID(Long id) {
		Order order = new Order();
		EntityManager em = getEntityManager();
		try {
			order = em.find(Order.class, id);
		} finally {
			em.close();
		}
		return order;
	}
	
			
	public List<Order> findOrdersByCustomerUsername(String username) {
		TypedQuery<Order> query =
				getEntityManager().createQuery("SELECT o from Order o "
						+ "JOIN o.sender s "
						+ "WHERE s.username = :username", Order.class);
		query.setParameter("username", username);
		return query.getResultList();
	}
	
	

	// Get a list of all users in the trainees_table
	public List<Order> findAllOrders() {

		TypedQuery<Order> query = 
				getEntityManager().createQuery("SELECT o from Order o", Order.class);

		return query.getResultList();
	}
	
	
//	public void updateOrder(Order order) {
//		Query query = getEntityManager().createQuery("UPDATE Order o SET o.receiverAddress=:receiverAddress");
//		//query.setParameter("receiverName", order.getReceiverName());
//		query.setParameter("receiverAddress", order.getReceiverAddress());
//		query.executeUpdate();
//	}
	
	public void updateOrder(Order order) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();	
		Order retrievedOrder = em.find(Order.class, order.getOrderId());
		et.begin();
		retrievedOrder.setReceiverName(order.getReceiverName());
		retrievedOrder.setReceiverAddress(order.getReceiverAddress());
		et.commit();
	}
	

}
