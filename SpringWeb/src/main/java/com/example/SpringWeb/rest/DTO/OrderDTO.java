package com.example.SpringWeb.rest.DTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
	private Integer client;
	private BigDecimal amount;
	private List<OrderItemDTO> nome;
}
