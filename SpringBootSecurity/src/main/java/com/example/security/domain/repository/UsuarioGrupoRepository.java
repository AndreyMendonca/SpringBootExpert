package com.example.security.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.security.domain.entity.Usuario;
import com.example.security.domain.entity.UsuarioGrupo;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, String>{
	
	@Query("""
		select distinct g.nome
		from UsuarioGrupo ug
		join ug.grupo g
		join ug.usuario u
		where u = ?1
	""")
	List<String> findPermissoesByUsuario(Usuario usuario);	
}
