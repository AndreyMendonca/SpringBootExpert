package com.example.SpringWeb.rest.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.example.SpringWeb.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
	@NotNull(message="Informe o código do cliente")
	private Integer client;
	@NotNull(message="Codigo total é obrigatório")
	private BigDecimal amount;
	@NotEmptyList(message = "Pedido não pode ser realizado sem itens")
	private List<OrderItemDTO> items;
}
