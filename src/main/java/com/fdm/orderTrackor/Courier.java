package com.fdm.orderTrackor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Table(name="COURIER")
@Entity
public class Courier {

	@Id
	@GeneratedValue
	@Column(name="COURIER_ID")
	private Long courierId;
	
	@Column(name="CUR_LOCATION")
	private String currentLocation;
	
	@Column
	private CourierStatus status;
	
	@OneToMany(mappedBy="courier", targetEntity=Order.class, fetch=FetchType.EAGER)
	private List<Order> orders;
	
	public Courier() {
		orders = new ArrayList<>();
	}
	
	public CourierStatus getStatus() {
		return status;
	}
	public void setStatus(CourierStatus status) {
		this.status = status;
	}
	public Long getCourierId() {
		return courierId;
	}
	public void setCourierId(Long courierId) {
		this.courierId = courierId;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {
		orders.remove(order);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courierId == null) ? 0 : courierId.hashCode());
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
		Courier other = (Courier) obj;
		if (courierId == null) {
			if (other.courierId != null)
				return false;
		} else if (!courierId.equals(other.courierId))
			return false;
		return true;
	}	
	
	
}
