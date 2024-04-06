package com.example.SpringWeb.service.impl;

import org.springframework.stereotype.Service;

import com.example.SpringWeb.domain.repository.OrderRepository;
import com.example.SpringWeb.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	private OrderRepository repository;
	
	public OrderServiceImpl(OrderRepository repository) {
		this.repository = repository;
	}
}
