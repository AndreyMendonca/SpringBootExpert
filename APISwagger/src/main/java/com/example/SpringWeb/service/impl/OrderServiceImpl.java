package com.example.SpringWeb.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.SpringWeb.domain.entity.Client;
import com.example.SpringWeb.domain.entity.Order;
import com.example.SpringWeb.domain.entity.OrderItem;
import com.example.SpringWeb.domain.entity.Product;
import com.example.SpringWeb.domain.enums.OrderStatus;
import com.example.SpringWeb.domain.repository.ClientRepository;
import com.example.SpringWeb.domain.repository.OrderItemRepository;
import com.example.SpringWeb.domain.repository.OrderRepository;
import com.example.SpringWeb.domain.repository.ProductRepository;
import com.example.SpringWeb.exception.OrderNotFoundException;
import com.example.SpringWeb.exception.RulesException;
import com.example.SpringWeb.rest.DTO.OrderDTO;
import com.example.SpringWeb.rest.DTO.OrderItemDTO;
import com.example.SpringWeb.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private final ClientRepository clientRepository;
	private final ProductRepository productRepository;
	private final OrderItemRepository orderItemRepository;

	@Override
	@Transactional
	public Order save(OrderDTO dto) {
		Integer idClient = dto.getClient();
		Client client = clientRepository
				.findById(idClient)
				.orElseThrow(() -> new RulesException("Código de cliente invalido"));
		
		Order order = new Order();
		order.setAmount(dto.getAmount());
		order.setOrderData(LocalDate.now());
		order.setClient(client);
		order.setOrderStatus(OrderStatus.ACTIVE);
		
		List<OrderItem> orderItems =  convertItems(order, dto.getItems());
		order.setOrderItem(orderItems);
		orderRepository.save(order);
		orderItemRepository.saveAll(orderItems);
		return order;
	}
	
	private List<OrderItem> convertItems(Order order, List<OrderItemDTO>items) {
		if(items.isEmpty()) {
			throw new RulesException("Não é possível realizar um pedido sem items");
		}
		return items
				.stream()
				.map(dto -> {
					Integer idProduct = dto.getProduct();
					Product product = productRepository
							.findById(idProduct)
							.orElseThrow(
								() -> new RulesException(
									"Código de produto inválido" + idProduct
								)
							);
					OrderItem orderItem = new OrderItem();
					orderItem.setQuantity(dto.getQuantity());
					orderItem.setOrder(order);
					orderItem.setProduct(product);
					return orderItem;
				}).collect(Collectors.toList());
	}

	@Override
	public Optional<Order> getOrderComplect(Integer id) {
		return orderRepository.findByIdFetchOrderItem(id);
	}

	@Override
	@Transactional
	public void updateOrderStatus(Integer id, OrderStatus orderStatus) {
		orderRepository
			.findById(id)
			.map(order -> {
				order.setOrderStatus(orderStatus);
				return orderRepository.save(order);
			}).orElseThrow(()-> new OrderNotFoundException());
		
	}
}
