package com.example.DataJPA.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.DataJPA.domain.entity.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ClientesJPA {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		entityManager.persist(cliente);
		return cliente;
	}
	
	@Transactional
	public Cliente atualizar(Cliente cliente) {
		entityManager.merge(cliente);
		return cliente;
	}
	
	@Transactional
	public void deletar(Cliente cliente) {
		if(!entityManager.contains(cliente)) {
			cliente = entityManager.merge(cliente);
		}
		entityManager.remove(cliente);
	}
	
	@Transactional
	public void deletarId(Integer id) {
		Cliente cliente = entityManager.find(Cliente.class, id);
		deletar(cliente);
	}
	
	//UPDATE = MERGE 
	//DELETAR POR ENTIDADE= REMOVE
	//DELETAR POR ID = DELETE
	//ENCONTRAR O CLIENTE = FIND(1 PARAMENTO = ENTIDADE.CLASS, 2 PARAMETRO = IDENTIFICADOR (ID)
	//readOnly = true == mapeando que é apenas leitura 
	
	@Transactional(readOnly = true)
	public List<Cliente> buscarPorNome(String nome){
		String jpql = "select c from Cliente c where c.nome like :nome";
		TypedQuery<Cliente> query = entityManager.createQuery(jpql,Cliente.class);
		query.setParameter("nome", "%"+nome+"%");
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> obterTodos(){
		return entityManager
				.createQuery("from Cliente",Cliente.class)
				.getResultList();
	}
}
