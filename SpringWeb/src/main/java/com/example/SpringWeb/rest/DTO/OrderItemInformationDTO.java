package com.example.SpringWeb.rest.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemInformationDTO {
	private String productDescription;
	private BigDecimal value;
	private Integer quantity;
	
}
