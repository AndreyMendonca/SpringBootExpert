package com.example.SpringWeb.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringWeb.domain.entity.Group;
import com.example.SpringWeb.domain.repository.GroupRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
	
	@Autowired
	private GroupRepository repository;
	
	@PostMapping
	@Transactional
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<Group> save (@RequestBody Group group){
		repository.save(group);
		 return ResponseEntity.ok(group);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<List<Group>> findAll(){
		return ResponseEntity.ok(repository.findAll());
	}
}
