package com.example.SpringWeb.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class PasswordMasterAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var login = authentication.getName();
		var password = (String) authentication.getCredentials();
		
		String loginMaster = "master";
		String passwordMaster = "1234";
		
		if(loginMaster.equals(login) && passwordMaster.equals(password)) {
			return new UsernamePasswordAuthenticationToken(
					"User Master", 
					null, 
					List.of(new SimpleGrantedAuthority("ADMIN"))
			);
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return true;
	}

}
