package com.example.SpringWeb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringWeb.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
