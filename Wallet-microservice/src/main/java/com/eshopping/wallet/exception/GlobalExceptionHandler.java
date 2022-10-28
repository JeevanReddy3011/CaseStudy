package com.eshopping.wallet.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(WalletCreationException.class)
	public ResponseEntity<ErrorDetails> handleInvalidEmailFormat(WalletCreationException ex) {
		ErrorDetails error = new ErrorDetails(
						ex.getMessage(), 
						LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}

}
