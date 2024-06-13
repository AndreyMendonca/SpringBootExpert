package com.example.agendamento.rest.controller;

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

import com.example.agendamento.domain.entity.Paciente;
import com.example.agendamento.domain.service.PacienteService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/paciente")
@Tag(name="Paciente", description = "Endpoints para classe paciente")
public class PacienteController {
	
	@Autowired
	private PacienteService service;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente save (@RequestBody Paciente paciente){
		Paciente obj = service.save(paciente);
		return obj;
	}
	
	@GetMapping("/{identificacao}")
	public ResponseEntity<Paciente> getById(@PathVariable() Integer identificacao){
		Paciente obj = service.findById(identificacao);
		return ResponseEntity.ok().body(obj);
	}
}
