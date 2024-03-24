package com.example.DataJPA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.DataJPA.domain.entity.Cliente;
import com.example.DataJPA.domain.repository.ClientesJpaRepository;

@SpringBootApplication
public class SpringBootDataJpaApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClientesJpaRepository clientes) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Andrey");
			Cliente cliente2 = new Cliente();
			cliente2.setNome("Jorge");
			
			//SALVAR CLIENTE
			clientes.save(cliente);
			clientes.save(cliente2);
			
			List<Cliente> todosOsClientes = clientes.findAll();
			todosOsClientes.forEach(System.out::println);
			
			boolean existe = clientes.existsByNome("Andrey");
			System.out.println("Existe um cliente com o nome Andrey: " + existe);
				
			//ATUALIZAR CLIENTE
			todosOsClientes.forEach(c->{
				c.setNome(c.getNome() + " atualizado");
				clientes.save(c);
			});
			System.out.println("Clientes atualizados");
			todosOsClientes = clientes.findAll();
			todosOsClientes.forEach(System.out::println);
			
			//BUSCAR POR NOME
			System.out.println("Busca pelo cliente com nome 'And': ");
			clientes.findByNomeLike("Andrey").forEach(System.out::println);
			
			System.out.print("Busca pelo cliente com nome 'And': ");
			clientes.encontrarPorNome2("Andrey").forEach(System.out::println);
			
			
			//DELETAR TUDO
			clientes.findAll().forEach(c->{
				clientes.delete(c);
			});
			
			todosOsClientes = clientes.findAll();
			if(todosOsClientes.isEmpty()) {
				System.out.println("Deletado todos os clientes");
			}else {
				System.out.println("Erro ao deletar");
			}
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

}
