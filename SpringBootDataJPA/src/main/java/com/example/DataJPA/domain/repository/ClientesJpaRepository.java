package com.example.DataJPA.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DataJPA.domain.entity.Cliente;

public interface ClientesJpaRepository extends JpaRepository<Cliente, Integer>{
	
	//Query Methods
	List<Cliente> findByNomeLike(String nome);
	
	boolean existsByNome(String nome);
	
	void deleteByNome(String nome);
	
	//@Query
	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	//Como estamos fazendo uma transação no banco, precisamos da anotattion @Modifying
	@Query("delete from Cliente c where c.nome =:nome ")
	@Modifying
	void deletarPorNome(String nome);
	
	//sql native
	@Query(value = "select * from cliente c where c.nome like '%:nome%' ", nativeQuery=true)
	List<Cliente> encontrarPorNome2(@Param("nome") String nome);
}
