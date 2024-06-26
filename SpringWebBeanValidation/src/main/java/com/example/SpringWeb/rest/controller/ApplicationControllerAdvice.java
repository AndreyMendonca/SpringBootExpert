package com.example.SpringWeb.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.SpringWeb.exception.OrderNotFoundException;
import com.example.SpringWeb.exception.RulesException;
import com.example.SpringWeb.rest.APIErros;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RulesException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public APIErros handleRulesException(RulesException ex) {
		String msgErro = ex.getMessage();
		return new APIErros(msgErro);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public APIErros handleOrderNotFoundException(OrderNotFoundException ex) {
		return new APIErros(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public APIErros handleMethodNotValidException(MethodArgumentNotValidException ex) {
		
		List<String> errors = ex.getBindingResult()
									.getAllErrors()
									.stream()
									.map(erro -> erro.getDefaultMessage())
									.collect(Collectors.toList());
		return new APIErros(errors);
	}
}
