package com.example.SpringWeb.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.SpringWeb.domain.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	private LocalDate OrderData;
	
	private BigDecimal amount;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItem; 
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	public Order() {}

	public Order(Integer id, Client client, BigDecimal amount) {
		super();
		this.id = id;
		//this.client = client;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", client= , amount=" + amount + "]";
	}

	public LocalDate getOrderData() {
		return OrderData;
	}

	public void setOrderData(LocalDate orderData) {
		OrderData = orderData;
	}
	
	
}
