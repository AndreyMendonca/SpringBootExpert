package com.example.SpringWeb.service;

import java.util.Optional;

import com.example.SpringWeb.domain.entity.Order;
import com.example.SpringWeb.domain.enums.OrderStatus;
import com.example.SpringWeb.rest.DTO.OrderDTO;

public interface OrderService {
	Order save(OrderDTO dto);
	
	Optional<Order> getOrderComplect(Integer id);
	
	void updateOrderStatus(Integer id, OrderStatus orderStatus);
}
