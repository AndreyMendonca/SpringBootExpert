package com.example.SpringWeb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringWeb.domain.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
