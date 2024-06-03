package com.example.security.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	Optional<Usuario> findByLogin(String login);
}
