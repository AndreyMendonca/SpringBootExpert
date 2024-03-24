package com.example.DataJPA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.DataJPA.domain.entity.Cliente;
import com.example.DataJPA.domain.repository.Clientes;

@SpringBootApplication
public class SpringBootDataJpaApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Andrey");
			clientes.salvar(cliente);
			
			Cliente cliente2 = new Cliente();
			
			//salvar
			cliente2.setNome("Jorge");
			clientes.salvar(cliente2);
			
			List<Cliente> todosClientes =  clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			
			//update
			todosClientes.forEach(c->{
				c.setNome(c.getNome()+ " atualizado");
				clientes.atualizar(c);
			});
			todosClientes =  clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			
			//buscar cliente
			System.out.print("Busca por cliente com 'and' = ");
			clientes.buscarPorNome("And").forEach(System.out::println);
			
			//deletando cliente
			clientes.obterTodos().forEach(c->{
				clientes.deletar(c.getId());
			});		
			todosClientes =  clientes.obterTodos();
			if(todosClientes.isEmpty()) {
				System.out.println("Todos os clientes deletesdos");
			}else {
				System.out.println("Fala ao deletar clientes");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

}
