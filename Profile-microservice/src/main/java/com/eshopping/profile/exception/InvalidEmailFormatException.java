package com.eshopping.profile.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidEmailFormatException extends RuntimeException {
	
	private final String message;
	

}
