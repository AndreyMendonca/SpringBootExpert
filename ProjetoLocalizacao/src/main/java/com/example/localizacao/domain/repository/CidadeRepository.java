package com.example.localizacao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.localizacao.domain.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	//strings
	List<Cidade> findByNome(String nome);
	
	@Query("select c from Cidade c where upper(c.nome) like upper(?1)")
	List<Cidade> findByNomeLike(String nome);
	
	List<Cidade> findByNomeStartingWith(String nome);
	
	List<Cidade> findByNomeEndingWith(String nome);
	
	List<Cidade> findByNomeContaining(String nome);
	
	//numeros
	List<Cidade> findByHabitantesLessThan(Long habitantes);
	
	List<Cidade> findByHabitantesGreaterThan(Long habitantes);
}
