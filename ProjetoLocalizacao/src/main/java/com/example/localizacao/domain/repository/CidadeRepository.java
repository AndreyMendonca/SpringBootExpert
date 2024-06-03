package com.example.localizacao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.localizacao.domain.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
