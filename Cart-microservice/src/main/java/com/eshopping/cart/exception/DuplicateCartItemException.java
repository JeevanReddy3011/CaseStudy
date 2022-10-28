package com.eshopping.cart.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DuplicateCartItemException extends RuntimeException {

	private String message;
	
}
