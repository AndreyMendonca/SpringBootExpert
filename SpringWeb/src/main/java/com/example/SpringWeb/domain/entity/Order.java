package com.example.SpringWeb.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/*
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client; */
	
	private BigDecimal amount;
	
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", client= , amount=" + amount + "]";
	}
	
	
}
