package com.eshopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private int productId;
	private String productName;
	private double price;
	private String availabilityStatus;
	private String productType;
	private String description;
	private String image;
	
}
