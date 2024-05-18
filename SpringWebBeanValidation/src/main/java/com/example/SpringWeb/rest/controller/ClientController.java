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

import com.example.SpringWeb.domain.entity.Client;
import com.example.SpringWeb.domain.repository.ClientRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientRepository repository;
	
	@GetMapping("/{id}")
	public Client getClientById(@PathVariable Integer id) {
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
	public Client save(@RequestBody @Valid Client client){
		return repository.save(client);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		repository.findById(id)
			.orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, 
					"Não encontrado"));
		
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update( @PathVariable Integer id, @RequestBody Client client){
		repository.findById(id)
			.map(clientExist -> {
				client.setId(clientExist.getId());
				repository.save(client);
				return clientExist;
			}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não encontrado"));
	}
	
	@GetMapping
	public List<Client> find(Client filter) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Client> example = Example.of(filter, matcher);
		return repository.findAll(example);
	}
	
}
