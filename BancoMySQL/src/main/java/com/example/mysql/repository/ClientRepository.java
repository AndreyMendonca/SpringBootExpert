package com.example.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mysql.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
