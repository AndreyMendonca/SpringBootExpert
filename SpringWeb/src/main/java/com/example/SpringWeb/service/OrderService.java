package com.example.SpringWeb.service;

import com.example.SpringWeb.domain.entity.Order;
import com.example.SpringWeb.rest.DTO.OrderDTO;

public interface OrderService {
	Order save(OrderDTO dto); 
}
