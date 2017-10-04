package com.fdm.orderTrackor;

import java.util.Date;

import javax.persistence.*;

@Table(name="delivery_order")
@Entity
public class Order {
	@Id
	@GeneratedValue
	@Column(name="ORDER_ID")
	private Long orderId;
	
	@ManyToOne
	@JoinColumn(name="CUST_ID")
	private Customer sender;
	
	@Column(name="RECEIVER_NAME")
	private String receiverName;
	
	@Column(name="RECEIVER_ADDRESS")
	private String receiverAddress;
	
	
	@ManyToOne
	@JoinColumn(name="COURIER_ID")
	private Courier courier;
	
	
	@Column(name="ORDER_DATE")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	
	@Column(name="ESTIMATE_DELIVERY")
	@Temporal(TemporalType.DATE)
	private Date estimateDeliveryDate;
	
	
	@Column(name="DELIVERY_DATE")
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;
	
	@Column
	private double cost;
	
	@Column
	private OrderStatus status;
	
	
	
	public Customer getSender() {
		return sender;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long order_id) {
		this.orderId = order_id;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public void setSender(Customer sender) {
		this.sender = sender;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	
	
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getEstimateDeliveryDate() {
		return estimateDeliveryDate;
	}
	public void setEstimateDeliveryDate(Date estimateDeliveryDate) {
		this.estimateDeliveryDate = estimateDeliveryDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}	
}
