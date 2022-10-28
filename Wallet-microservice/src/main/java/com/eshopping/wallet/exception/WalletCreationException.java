package com.eshopping.wallet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WalletCreationException extends RuntimeException {

	private String message;
	
}
