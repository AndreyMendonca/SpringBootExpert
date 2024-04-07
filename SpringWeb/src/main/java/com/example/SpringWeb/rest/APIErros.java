package com.example.SpringWeb.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;


public class APIErros {
	@Getter
	private List<String> errors;
	
	public APIErros(String messageError) {
		this.errors = Arrays.asList(messageError);
	}
}
