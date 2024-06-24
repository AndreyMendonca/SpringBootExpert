package com.example.jwt.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
