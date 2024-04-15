package com.example.SpringWeb.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringWeb.domain.entity.Group;
import com.example.SpringWeb.domain.entity.User;
import com.example.SpringWeb.domain.entity.UserGroup;
import com.example.SpringWeb.domain.repository.GroupRepository;
import com.example.SpringWeb.domain.repository.UserGroupRepository;
import com.example.SpringWeb.domain.repository.UserRepository;
import com.example.SpringWeb.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final GroupRepository groupRepository;
	private final UserGroupRepository userGroupRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public User save(User user, List<String> groups) {
		String passwordCripto = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordCripto);
		userRepository.save(user);
		
		List<UserGroup> listUserGroup = groups.stream().map(nameGroup -> {
			Optional<Group> possibleGroup = groupRepository.findByName(nameGroup);
			if(possibleGroup.isPresent()) {
				Group group = possibleGroup.get();
				return new UserGroup(user,group);
			}
			return null;
		}).filter(group -> group != null).collect(Collectors.toList());
		userGroupRepository.saveAll(listUserGroup);
		
		return user;
		
	}

	@Override
	public User getUserWithPermissions(String login) {
		Optional<User> userOptional = userRepository.findByLogin(login);
		if(userOptional.isEmpty()) {
			return null;
		}
		
		User user = userOptional.get();
		List<String> permissions = userGroupRepository.findPermitionsByUser(user);
	
		user.setPermissions(permissions);
		
		return user;
	}
	
	
	
}
