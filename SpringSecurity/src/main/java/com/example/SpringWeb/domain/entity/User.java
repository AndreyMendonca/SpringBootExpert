package com.example.SpringWeb.domain.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name="tb_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String login;
	private String password;
	private String name;
	private String lastName;
	
	@Transient
	private List<String> permissions;
}
