package com.example.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.mysql.entities.Client;
import com.example.mysql.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	private ClientService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Integer id){
		Client client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@RequestBody Client client){
		return service.save(client);
	}
}
