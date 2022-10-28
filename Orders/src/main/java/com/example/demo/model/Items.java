package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Items {

	private int itemId;
	private int cartId;
	private String productName;
	private double price;
	private int quantity;
	
}
