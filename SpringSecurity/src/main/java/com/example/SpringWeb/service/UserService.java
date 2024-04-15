package com.example.SpringWeb.service;

import java.util.List;

import com.example.SpringWeb.domain.entity.User;

public interface UserService {
	public User save(User user, List<String> permitions);
	
	public User getUserWithPermissions(String login);
}
