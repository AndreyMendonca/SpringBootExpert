package com.example.SpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.SpringWeb.domain.entity.Client;
import com.example.SpringWeb.domain.repository.ClientRepository;

@SpringBootApplication
public class SpringWebApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClientRepository repository) {
		return args ->{
			Client c = new Client(null, "Andrey");
			
			repository.save(c);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

}
