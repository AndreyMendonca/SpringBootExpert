package com.example.SpringWeb.domain.entity;

import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 100)
	@NotEmpty(message = "Campo nome é obrigatório")
	private String name;
	
	@Column(length = 11)
	@NotEmpty(message = "Campo CPF é obrigatório")
	@CPF(message = "Informe um CPF valido")
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private Set<Order> orders; 
	
	public Client() {
	}

	public Client(Integer id, String name, String cpf) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<Order> getOrders() {
		return orders;
	}
	
	
}
