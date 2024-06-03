package com.example.localizacao.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.localizacao.domain.entity.Cidade;
import com.example.localizacao.domain.service.CidadeService;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade save(@RequestBody Cidade cidade) {
		return service.save(cidade);
	}
	
	@GetMapping
	public List<Cidade> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Cidade findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow( 
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não localizado"));
	}
}
