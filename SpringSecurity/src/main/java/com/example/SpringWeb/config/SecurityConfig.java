package com.example.SpringWeb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig{
	
	//role -> grupo de usuario -> master, ferente, frente de loja
	//authority -> premissoes -> cadastrar usuarios, acessar rela de relatorio ... 
	//role é um grupo de authority
	
	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http, 
			PasswordMasterAuthenticationProvider passwordMasterAuthenticationProvides,
			CustomFilter customFilter) throws Exception{
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(customizer -> {
					customizer.requestMatchers("/api/client").permitAll();
					customizer.anyRequest().authenticated();
				})
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				.authenticationProvider(passwordMasterAuthenticationProvides)
				.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	//criando usuarios em memmorias
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails commonUser = User.builder()
				.username("user")
				.password(passwordEncoder().encode("123"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(commonUser,admin);
	}
	
	//criptogrfando a senha
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//remover o prefixo que o spring boot exige na frente de role ("admin" -> ROLE_ADMIN)
	@Bean
	public GrantedAuthorityDefaults grantedAuthoriryDefaults() {
		return new GrantedAuthorityDefaults("");
	}
	
}
