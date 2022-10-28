package com.eshopping.cart.model;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(description = "Details of the item in the cart")
public class Items {

	@Id
	@ApiModelProperty(notes = "Unique item Id")
	private int itemId;
	
	@ApiModelProperty(notes = "Id of the cart in which the item is added to")
	private int cartId;
	
	@ApiModelProperty(notes = "Name of the product in the cart")
	private String productName;
	
	@ApiModelProperty(notes = "Price of the item")
	private double price;
	
	@ApiModelProperty(notes = "The quantity of the item added in the cart")
	private int quantity;
	
	@ApiModelProperty(notes = "The availability status of the item added in the cart")
	private String availabilityStatus;
	
	@ApiModelProperty(notes = "The image of the item in the cart")
	private String image;
	
}
