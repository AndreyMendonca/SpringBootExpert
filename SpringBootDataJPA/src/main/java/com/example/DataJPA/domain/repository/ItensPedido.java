package com.example.DataJPA.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DataJPA.domain.entity.ItemPedido;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer>{

}
