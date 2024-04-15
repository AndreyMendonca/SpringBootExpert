package com.example.SpringWeb.rest.DTO;

import java.util.List;

import com.example.SpringWeb.domain.entity.User;

import lombok.Data;

@Data
public class UserDTO {
	private User user;
	private List<String> permitions;
}
