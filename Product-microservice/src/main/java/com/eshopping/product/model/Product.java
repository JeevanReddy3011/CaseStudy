package com.eshopping.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private int productId;
	private String productName;
	private double price;
	private String availabilityStatus;
	private String productType;
	private String description;
	private String image;
	
}
