package com.example.DataJPA.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DataJPA.domain.entity.Cliente;

public interface ClientesJpaRepository extends JpaRepository<Cliente, Integer>{
	
	List<Cliente> findByNomeLike(String nome);
	
	boolean existsByNome(String nome);
}
