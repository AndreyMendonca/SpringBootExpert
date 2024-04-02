package com.example.SpringWeb.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SpringWeb.domain.entity.Client;
import com.example.SpringWeb.domain.repository.ClientRepository;

@Controller
@RequestMapping("/api/clients")
public class ClientControllerRefactore {
	
	@Autowired
	private ClientRepository repository;

	/* MODELO
	@RequestMapping(
			value="/hello/{nome}", 
			method = RequestMethod.GET, 
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"}
	)
	@ResponseBody
	public String helloCliente(@PathVariable("nome") String nomeCliente) {
		return nomeCliente;
	}
	*/
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
		Optional<Client> client = repository.findById(id);
		if(client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Client> save(@RequestBody  Client client){
		Client clientSave = repository.save(client);
		return ResponseEntity.ok(clientSave);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Client> delete(@PathVariable Integer id){
		Optional<Client> client = repository.findById(id);
		if(client.isPresent()) {
			repository.delete(client.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity update(
			@PathVariable Integer id, 
			@RequestBody Client client){
		return repository.findById(id)
				.map( clientExist -> {
					client.setId(clientExist.getId());
					repository.save(client);
					return ResponseEntity.noContent().build();
				}).orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
	@GetMapping
	public ResponseEntity find(Client filter) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example example = Example.of(filter, matcher);
		List<Client> clients = repository.findAll(example);
		return ResponseEntity.ok(clients);
	}
	
}
