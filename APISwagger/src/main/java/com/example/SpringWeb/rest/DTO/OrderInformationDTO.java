package com.example.SpringWeb.rest.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInformationDTO {
	private Integer code;
	private String cpf;
	private String clientName;
	private BigDecimal amount;
	private String date;
	private String orderStatus;
	private List<OrderItemInformationDTO> items;
}
