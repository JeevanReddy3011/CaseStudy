package com.eshopping.cart.model;

import java.util.List;

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
@ApiModel(description = "List of all items in the cart with the cart Id and total amount")
public class CartItems {
	@ApiModelProperty(notes = "Id of the cart where items are added")
	private int cartId;
	
	@ApiModelProperty(notes = "List of items in the cart")
	private List<Items> itemList;
	
	@ApiModelProperty(notes = "Total amount of the cart")
	private double totalAmount;

}
