package com.example.agendamento.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agendamento.domain.entity.Paciente;
import com.example.agendamento.domain.repository.PacienteRepository;
import com.example.agendamento.domain.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteRepository repository;
	
	@Override
	public Paciente save(Paciente paciente) {
		return repository.save(paciente);
	}

	@Override
	public Paciente findById(Integer id) {
		Optional<Paciente> paciente = repository.findById(id);
		return paciente.get();
	}

	@Override
	public List<Paciente> findAll() {
		return repository.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Paciente update(Paciente paciente, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
