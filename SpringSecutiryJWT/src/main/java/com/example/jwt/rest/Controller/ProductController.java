package com.example.jwt.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.domain.entity.Product;
import com.example.jwt.domain.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product save(@RequestBody Product product) {
		return service.save(product);
	}
	
	@GetMapping
	public List<Product> findAll(){
		return service.findAll();
	}
	
}
