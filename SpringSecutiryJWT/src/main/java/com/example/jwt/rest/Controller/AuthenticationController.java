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

import com.example.jwt.config.secutiry.TokenService;
import com.example.jwt.domain.entity.DTO.AuthenticationDTO;
import com.example.jwt.domain.entity.DTO.LoginResponseDTO;
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
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticationDTO user) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(user.login(), user.passwordUser());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Users) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity register(@RequestBody AuthenticationDTO user) {
		if(this.repository.findByLogin(user.login()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ou senha incorretos");
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(user.passwordUser());
		Users newUser = new Users(user.login(),encryptedPassword, UserRole.ADMIN);
		
		this.repository.save(newUser);
		
		return ResponseEntity.ok().body(newUser);
	}
	
}
