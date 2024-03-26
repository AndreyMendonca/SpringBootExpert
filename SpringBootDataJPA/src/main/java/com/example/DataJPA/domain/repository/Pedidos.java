package com.example.DataJPA.domain.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DataJPA.domain.entity.Cliente;
import com.example.DataJPA.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{
	
	Set<Pedido> findByCliente (Cliente cliente);
}
