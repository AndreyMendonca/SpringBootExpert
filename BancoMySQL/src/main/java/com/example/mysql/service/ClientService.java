package com.example.mysql.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mysql.entities.Client;
import com.example.mysql.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;
	
	public Client findById(Integer id) {
		Optional<Client> client = repository.findById(id);
		return client.get();
	}
	public Client save(Client client) {
		return repository.save(client);
	}
}
