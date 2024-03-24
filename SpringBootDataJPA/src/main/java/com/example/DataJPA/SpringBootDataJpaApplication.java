package com.example.DataJPA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.DataJPA.domain.entity.Cliente;
import com.example.DataJPA.domain.repository.ClientesJPA;

@SpringBootApplication
public class SpringBootDataJpaApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClientesJPA clientes) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Andrey");
			Cliente cliente2 = new Cliente();
			cliente2.setNome("Jorge");
			
			//SALVAR CLIENTE
			clientes.salvar(cliente);
			clientes.salvar(cliente2);
			
			List<Cliente> todosOsClientes = clientes.obterTodos();
			todosOsClientes.forEach(System.out::println);
			
			//ATUALIZAR CLIENTE
			todosOsClientes.forEach(c->{
				c.setNome(c.getNome() + " atualizado");
				clientes.atualizar(c);
			});
			System.out.println("Clientes atualizados");
			todosOsClientes = clientes.obterTodos();
			todosOsClientes.forEach(System.out::println);
			
			//BUSCAR POR NOME
			System.out.print("Busca pelo cliente com nome 'And': ");
			todosOsClientes = clientes.buscarPorNome("And");
			todosOsClientes.forEach(System.out::println);
			
			//DELETAR TUDO
			clientes.obterTodos().forEach(c->{
				clientes.deletar(c);
			});
			
			todosOsClientes = clientes.obterTodos();
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
