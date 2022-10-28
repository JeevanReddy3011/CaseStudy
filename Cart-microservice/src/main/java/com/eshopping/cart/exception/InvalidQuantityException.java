package com.eshopping.cart.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InvalidQuantityException extends RuntimeException {
	
	private String message;

}
