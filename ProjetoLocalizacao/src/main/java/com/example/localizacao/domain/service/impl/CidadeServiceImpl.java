package com.example.localizacao.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
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
		//Pageable pageable = PageRequest.of(0, 2);
		
		//Ordenado
		return repository.findByNomeLike("%" + nome + "%", Sort.by("habitantes"));
		
		//paginado
		//return (List<Cidade>) repository.findByNomeLike("%" + nome + "%", pageable);
	}

	@Override
	public List<Cidade> filtroDinamico(Cidade cidade) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
		Example<Cidade> example = Example.of(cidade,matcher);
		return repository.findAll(example);
	}

}
