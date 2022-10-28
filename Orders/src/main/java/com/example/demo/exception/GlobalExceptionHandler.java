package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(InsufficientWalletFundsException.class)
	public ResponseEntity<ErrorDetails> handleInvalidEmailFormat(InsufficientWalletFundsException ex) {
		ErrorDetails error = new ErrorDetails(
						ex.getMessage(), 
						LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(OrderPlacementException.class)
	public ResponseEntity<ErrorDetails> handleInvalidEmailFormat(OrderPlacementException ex) {
		ErrorDetails error = new ErrorDetails(
						ex.getMessage(), 
						LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}
	
}
