package com.example.agendamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agendamento.domain.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

}
