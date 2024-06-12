package com.example.agendamento.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.agendamento.domain.entity.Paciente;

@Service
public interface PacienteService {
	Paciente save(Paciente paciente);
	Paciente findById(Integer id);
	List<Paciente> findAll();
	void delete(Integer id);
	Paciente update(Paciente paciente, Integer id);
}
