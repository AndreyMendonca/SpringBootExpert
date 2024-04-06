package com.example.SpringWeb.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringWeb.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private OrderService service;
	
	public OrderController(OrderService service) {
		this.service = service;
	}
	
}
