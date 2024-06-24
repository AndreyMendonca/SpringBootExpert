package com.example.jwt.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.domain.entity.Product;
import com.example.jwt.domain.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	public Product save(Product product) {
		return repository.save(product);
	}
	
	public List<Product> findAll(){
		return repository.findAll();
	}
}
