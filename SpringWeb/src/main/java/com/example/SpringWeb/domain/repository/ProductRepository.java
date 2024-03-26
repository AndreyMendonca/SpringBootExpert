package com.example.SpringWeb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringWeb.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
