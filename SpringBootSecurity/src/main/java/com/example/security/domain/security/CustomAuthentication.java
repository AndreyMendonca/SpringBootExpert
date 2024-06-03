package com.example.security.domain.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomAuthentication implements Authentication{

	private static final long serialVersionUID = 1L;
	
	private final IdentificacaoUsuario identificaoUsuario;
	
	public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
		if(identificacaoUsuario == null) {
			throw new ExceptionInInitializerError("Não foi possivel criar um customAuthentication sem identificacao do usuario");
			
		}
		
		this.identificaoUsuario = identificacaoUsuario;
	}
	
	@Override
	public String getName() {
		return this.identificaoUsuario.getNome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.identificaoUsuario
				.getPermissoes()
				.stream()
				.map(perm -> new SimpleGrantedAuthority(perm))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.identificaoUsuario;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		throw new IllegalArgumentException("Não precisa chamar já estamos autenticados");
	}
	

}
