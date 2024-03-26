package com.example.SpringWeb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringWeb.domain.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
