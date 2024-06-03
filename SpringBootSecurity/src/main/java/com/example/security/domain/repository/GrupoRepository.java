package com.example.security.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.domain.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, String>{
	Optional<Grupo> findByNome(String nome);
}
