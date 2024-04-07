package com.example.SpringWeb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.SpringWeb.domain.entity.Order;
import java.util.List;
import java.util.Optional;

import com.example.SpringWeb.domain.entity.Client;


public interface OrderRepository extends JpaRepository<Order, Integer>{
	List<Order> findByClient(Client client);
	
	@Query("select o from Order o left join fetch o.orderItem where o.id = :id")
	Optional<Order> findByIdFetchOrderItem(@Param("id") Integer id);
}
