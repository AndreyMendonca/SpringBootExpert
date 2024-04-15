package com.example.SpringWeb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringWeb.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	Optional<User> findByLogin(String login);
}
