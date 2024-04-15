package com.example.SpringWeb.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringWeb.domain.entity.User;
import com.example.SpringWeb.rest.DTO.UserDTO;
import com.example.SpringWeb.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;	
	
	@PostMapping
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<User> save(@RequestBody UserDTO body){
		User userSave = userService.save(body.getUser(), body.getPermitions());
		return ResponseEntity.ok(userSave);
	}
	
}
