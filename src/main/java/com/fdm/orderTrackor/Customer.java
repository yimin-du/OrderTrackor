package com.fdm.orderTrackor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Customer {

	@Id
	@Column(name="CUST_ID")
	@GeneratedValue
	private Long customerId;

	@Column(length=50, nullable=false)
	private String username;

	@Column(length=50, nullable=false)
	private String password;

	@Column(length=50, nullable=false)
	private String name;

	@OneToMany(mappedBy="sender", targetEntity=Order.class, fetch=FetchType.EAGER)
	private List<Order> orders;

	public Customer() {
		orders = new ArrayList<>();
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	public void addOrder(Order order) {
		this.orders.add(order);
		if(order.getSender() != this) {
			order.setSender(this);
		}
	}
	
	public void removeOrder(Order order) {
		this.orders.remove(order);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}	

	
}
