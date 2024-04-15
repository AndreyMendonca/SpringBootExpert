package com.example.SpringWeb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringWeb.domain.entity.Group;

public interface GroupRepository extends JpaRepository<Group, String>{
	Optional<Group> findByName(String name);
}
