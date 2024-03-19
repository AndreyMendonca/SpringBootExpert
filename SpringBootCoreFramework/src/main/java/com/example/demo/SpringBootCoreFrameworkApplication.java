package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootCoreFrameworkApplication {
	/*
	@Autowired
	@Qualifier("applicationName")
	public String applicationName;
	*/
	
	//injetar uma propriedade criada la no application.properties
	@Value("${application.name}")
	public String applicationName;
	
	@GetMapping("/hello")
	public String helloWorld() {
		return applicationName;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCoreFrameworkApplication.class, args);
	}

}
