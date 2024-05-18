package com.example.SpringWeb.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.SpringWeb.domain.entity.Product;
import com.example.SpringWeb.domain.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	private ProductRepository repository;
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return repository
				.findById(id).orElseThrow( 
						() -> new ResponseStatusException(
								HttpStatus.NOT_FOUND,
								"Cliente não encontrado"
							)
						);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product save(@RequestBody @Valid Product product) {
		return repository.save(product);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		repository.findById(id)
			.orElseThrow(() -> { 
				ResponseStatusException ex = new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Produto não encontrado");
				System.out.println(ex.getMessage());
				return ex;
			});
		
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody @Valid Product product, @PathVariable Integer id) {
		repository.findById(id)
			.map(productNew -> {
				product.setId(productNew.getId());
				repository.save(product);
				return productNew;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Produto não encontrado"));
	}
	
	@GetMapping
	public List<Product> find(Product filter){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Product> example = Example.of(filter,matcher);
		return repository.findAll(example);
	}
	
}
