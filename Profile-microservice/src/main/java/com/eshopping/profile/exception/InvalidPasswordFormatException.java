package com.eshopping.profile.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidPasswordFormatException extends RuntimeException {

	private final String message;
	
}
