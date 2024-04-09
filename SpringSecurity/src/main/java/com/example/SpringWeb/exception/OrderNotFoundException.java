package com.example.SpringWeb.exception;

public class OrderNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException() {
		super("Order not found");
	}
}
