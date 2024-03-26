package com.example.DataJPA.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DataJPA.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{

}
