package com.example.DataJPA;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.DataJPA.domain.entity.Cliente;
import com.example.DataJPA.domain.entity.Pedido;
import com.example.DataJPA.domain.repository.ClientesJpaRepository;
import com.example.DataJPA.domain.repository.Pedidos;

@SpringBootApplication
public class SpringBootDataJpaApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClientesJpaRepository clientes, @Autowired Pedidos pedidos) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Andrey");
			clientes.save(cliente);
			
			Pedido p = new Pedido();
			p.setClient(cliente);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			
			pedidos.save(p);
			
			Cliente c = clientes.findClienteFetchPedido(cliente.getId());
			System.out.println(c);
			System.out.println(c.getPedidos());		
			
			pedidos.findByCliente(cliente).forEach(System.out::println);
			
			/*
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
			*/
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

}
