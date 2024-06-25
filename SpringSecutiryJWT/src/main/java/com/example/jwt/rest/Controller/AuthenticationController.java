package com.example.jwt.rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.jwt.domain.entity.DTO.AuthenticationDTO;
import com.example.jwt.domain.entity.user.UserRole;
import com.example.jwt.domain.entity.user.Users;
import com.example.jwt.domain.repository.UsersRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsersRepository repository;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticationDTO user) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(user.login(), user.passwordUser());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Users register(@RequestBody AuthenticationDTO user) {
		if(this.repository.findByLogin(user.login()) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(user.passwordUser());
		Users newUser = new Users(user.login(),encryptedPassword, UserRole.ADMIN);
		
		this.repository.save(newUser);
		
		return newUser;
	}
	
}
