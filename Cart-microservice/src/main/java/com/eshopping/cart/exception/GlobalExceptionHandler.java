package com.eshopping.cart.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eshopping.cart.exception.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleInvalidEmailFormat(ProductNotFoundException ex) {
		ErrorDetails error = new ErrorDetails(
						ex.getMessage(), 
						LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(DuplicateCartItemException.class)
	public ResponseEntity<ErrorDetails> handleInvalidEmailFormat(DuplicateCartItemException ex) {
		ErrorDetails error = new ErrorDetails(
						ex.getMessage(), 
						LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InvalidQuantityException.class)
	public ResponseEntity<ErrorDetails> handleInvalidEmailFormat(InvalidQuantityException ex) {
		ErrorDetails error = new ErrorDetails(
						ex.getMessage(), 
						LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}

}
