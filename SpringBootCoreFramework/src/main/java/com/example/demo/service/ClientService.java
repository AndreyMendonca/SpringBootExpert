package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Client;
import com.example.demo.repository.ClientRepository;

public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public void save(Client client) {
		validarClient(client);
		repository.save();
	}
	public void validarClient(Client client) {
		//validar cliet
	}
}
