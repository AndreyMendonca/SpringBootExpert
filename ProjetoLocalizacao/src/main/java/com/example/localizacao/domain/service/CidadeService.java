package com.example.localizacao.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.localizacao.domain.entity.Cidade;

public interface CidadeService {
	Cidade save(Cidade cidade);
	
	List<Cidade> findAll();
	
	Optional<Cidade> findById(Long id);
}
