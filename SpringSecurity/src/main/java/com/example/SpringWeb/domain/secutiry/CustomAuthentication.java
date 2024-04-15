package com.example.SpringWeb.domain.secutiry;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomAuthentication implements Authentication{

	private static final long serialVersionUID = 1L;

	private final UserIdentification userIdentification;
	
	public CustomAuthentication(UserIdentification userIdentification) {
		if (userIdentification == null) {
			throw new ExceptionInInitializerError("Não foi possivel criar um customAuthentication sem a identificação do usuario");
		}
		this.userIdentification = userIdentification;
	}
	
	@Override
	public String getName() {
		return this.userIdentification.getName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.userIdentification
				.getPermissions()
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
		return this.userIdentification;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		throw new IllegalArgumentException("Não precisa chamar, já estamos autentificados");
		
	}

}
