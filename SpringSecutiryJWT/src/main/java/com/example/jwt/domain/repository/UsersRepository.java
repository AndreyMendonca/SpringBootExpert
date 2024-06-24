package com.example.jwt.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.jwt.domain.entity.user.Users;

public interface UsersRepository extends JpaRepository<Users, String>{
	UserDetails findByLogin(String login);
}
