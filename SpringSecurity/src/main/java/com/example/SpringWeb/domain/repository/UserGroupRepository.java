package com.example.SpringWeb.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SpringWeb.domain.entity.User;
import com.example.SpringWeb.domain.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, String>{
	
	@Query("""
			select distinct g.name
			from UserGroup up
			join up.Group g
			join ug.User u	 
			where u = ?1
			""")
	List<String> findPermitionsByUser(User user);
}
