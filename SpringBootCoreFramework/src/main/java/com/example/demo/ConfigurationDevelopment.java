package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class ConfigurationDevelopment {

	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("AMBIENTE DE DESENVOLVIMENTO");
		};
	}
}
