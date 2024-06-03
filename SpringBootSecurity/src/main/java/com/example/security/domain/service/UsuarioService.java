package com.example.security.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.domain.entity.Grupo;
import com.example.security.domain.entity.Usuario;
import com.example.security.domain.entity.UsuarioGrupo;
import com.example.security.domain.repository.GrupoRepository;
import com.example.security.domain.repository.UsuarioGrupoRepository;
import com.example.security.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;
	
	@Autowired
	private PasswordEncoder passwordEndocer;
	
	@Transactional
	public Usuario salvar(Usuario usuario, List<String> grupos) {
		String senhaCriptografada = passwordEndocer.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		repository.save(usuario);
		
		List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
			Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
			if(possivelGrupo.isPresent()) {
				Grupo grupo = possivelGrupo.get();
				return new UsuarioGrupo(usuario, grupo);
			}
			return null;
		}).filter(grupo -> grupo != null).collect(Collectors.toList());
		
		usuarioGrupoRepository.saveAll(listaUsuarioGrupo);
		
		return usuario;
	}
	
	public Usuario obterUsuarioComPermissoes(String login) {
		Optional<Usuario> usuarioEncontrado = repository.findByLogin(login);
		if(usuarioEncontrado.isEmpty()) {
			return null;
		}
		 Usuario usuario = usuarioEncontrado.get();
		 List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
		 usuario.setPermissoes(permissoes);
		 
		 return usuario;
	}
}
