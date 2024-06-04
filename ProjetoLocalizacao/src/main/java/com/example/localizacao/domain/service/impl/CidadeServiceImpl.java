package com.example.localizacao.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.localizacao.domain.entity.Cidade;
import com.example.localizacao.domain.repository.CidadeRepository;
import com.example.localizacao.domain.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService{
	
	@Autowired
	private CidadeRepository repository;
	
	@Override
	public Cidade save(Cidade cidade) {
		return repository.save(cidade);
	}

	@Override
	public List<Cidade> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Cidade> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Cidade> findByNome(String nome) {
		return repository.findByNomeLike("%" + nome + "%");
	}

}
