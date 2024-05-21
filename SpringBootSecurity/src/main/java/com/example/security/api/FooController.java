package com.example.security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class FooController {
	
	@GetMapping("/public")
	public ResponseEntity<String> publicRoute(){
		return ResponseEntity.ok("Public route ok");
	}
	
	@GetMapping("/private")
	public ResponseEntity<String> privateRoute(Authentication authentication){
		return ResponseEntity.ok("Private route ok!!\nSeja bem vindo: " + authentication.getName());
	}
}
