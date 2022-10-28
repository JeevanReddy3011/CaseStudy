package com.eshopping.profile.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(InvalidEmailFormatException.class)
	public ResponseEntity<ErrorDetails> handleInvalidEmailFormat(InvalidEmailFormatException ex) {
		ErrorDetails error = new ErrorDetails(
						ex.getMessage(), 
						LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(InvalidPasswordFormatException.class)
	public ResponseEntity<ErrorDetails> handleInvalidPasswordFormat(InvalidPasswordFormatException ex) {
		ErrorDetails error = new ErrorDetails(
				ex.getMessage(), 
				LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(DuplicateUsernameException.class)
	public ResponseEntity<ErrorDetails> handleDuplicateUsernameException(DuplicateUsernameException ex) {
		ErrorDetails error = new ErrorDetails(
				ex.getMessage(), 
				LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InvalidUsernameFormatException.class)
	public ResponseEntity<ErrorDetails> handleInvalidUsernameException(InvalidUsernameFormatException ex) {
		ErrorDetails error = new ErrorDetails(
				ex.getMessage(), 
				LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorDetails> BadCredentialsException(BadCredentialsException ex) {
		ErrorDetails error = new ErrorDetails(
				ex.getMessage(), 
				LocalDateTime.now());
		return new ResponseEntity<>(error,org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
	}
}
