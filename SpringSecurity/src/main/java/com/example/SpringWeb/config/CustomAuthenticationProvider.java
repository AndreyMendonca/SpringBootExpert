package com.example.SpringWeb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.SpringWeb.domain.entity.User;
import com.example.SpringWeb.domain.secutiry.CustomAuthentication;
import com.example.SpringWeb.domain.secutiry.UserIdentification;
import com.example.SpringWeb.service.UserService;

@Component
public class CustomAuthenticationProvider implements  AuthenticationProvider{

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user = userService.getUserWithPermissions(login);
		if(user != null) {
			boolean loginAuthentic = passwordEncoder.matches(password, user.getPassword());
			if(loginAuthentic) {
				UserIdentification userIdentification = new UserIdentification(
							user.getId(),
							user.getName(),
							user.getLogin(),
							user.getPermissions()
				);
				return new CustomAuthentication(userIdentification);	
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
