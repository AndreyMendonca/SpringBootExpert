package com.example.SpringWeb.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.SpringWeb.domain.entity.Order;
import com.example.SpringWeb.domain.entity.OrderItem;
import com.example.SpringWeb.domain.enums.OrderStatus;
import com.example.SpringWeb.rest.DTO.OrderDTO;
import com.example.SpringWeb.rest.DTO.OrderInformationDTO;
import com.example.SpringWeb.rest.DTO.OrderItemInformationDTO;
import com.example.SpringWeb.rest.DTO.UpdateOrderStatusDTO;
import com.example.SpringWeb.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private OrderService service;
	
	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody @Valid OrderDTO dto) {
		Order order = service.save(dto);
		return order.getId();
	}
	
	@GetMapping("/{id}")
	public OrderInformationDTO getByid(@PathVariable Integer id) {
		return service
				.getOrderComplect(id)
				.map( p -> orderConvert(p))
				.orElseThrow(() -> 
					new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateOrderStatus(@PathVariable Integer id, @RequestBody @Valid UpdateOrderStatusDTO dto) {
		String newStatus = dto.getNewOrderStatus();
		service.updateOrderStatus(id, OrderStatus.valueOf(newStatus));
	}
	
	private OrderInformationDTO orderConvert(Order order) {
		return OrderInformationDTO
			.builder()
			.code(order.getId())
			.date(order.getOrderData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
			.cpf(order.getClient().getCpf())
			.clientName(order.getClient().getName())
			.amount(order.getAmount())
			.orderStatus(order.getOrderStatus().name())
			.items(orderItemConvert(order.getOrderItem()))
			.build();
	}
	
	private List<OrderItemInformationDTO> orderItemConvert(List<OrderItem> items){
		if(CollectionUtils.isEmpty(items)) {
			return Collections.emptyList();
		}
		
		return items.stream().map(
				item -> OrderItemInformationDTO
					.builder()
					.productDescription(item.getProduct().getName())
					.value(item.getProduct().getPrice())
					.quantity(item.getQuantity())
					.build()
		).collect(Collectors.toList());
	}
	
}
