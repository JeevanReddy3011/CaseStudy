package com.eshopping.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.cart.model.Cart;
import com.eshopping.cart.model.CartItems;
import com.eshopping.cart.model.Items;
import com.eshopping.cart.service.CartService;
import com.eshopping.cart.service.ItemService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ItemService itemService;
	
	Cart cart = new Cart();
	
	@GetMapping("/get-items")
	@ApiOperation(
			value = "Gets all the cart items without the total amount of the cart",
			response = List.class
			)
	public List<Items> getCartItems(){
		return itemService.getAllCartItems();
	}
		
	@GetMapping("/get-item-by-id/{itemId}")
	@ApiOperation(
			value = "Gets the the item from the cart by the item Id",
			notes = "Provide an item Id to get the specific item from the cart",
			response = Items.class
			)
	public Items getItemById(@PathVariable("itemId")int itemId) {
		return itemService.getItemByItemId(itemId);
	}
	
	@GetMapping("/view-all-items")
	@ApiOperation(
			value = "Gets all the cart items with the total amount of the cart",
			response = CartItems.class
			)
	public CartItems getAllCartItems(){
		return new CartItems(cart.getCartId(), 
							 itemService.getAllCartItems() , 
							 cartService.getTotalAmount());
	}
	
//	@GetMapping("/get-message-producer")
//	public Product getMessageFromProducer() {
//		MessageListener message = new MessageListener();
//		return message.listener(getMessageFromProducer());
//	}
	
	@PostMapping("/create-cart")
	@ApiOperation(
			value = "Creates a cart with an Id and total amount set to 0.0",
			response = Cart.class
			)
	public Cart createNewCart() {
		return cartService.createCart(cart);
	}
	
	@PostMapping("/add-items/{itemId}")
	@ApiOperation(
			value = "Adds an item to the cart by the item Id provided",
			notes = "Provide the right itemId to add the item to the cart",
			response = Items.class
			)
	public Items addItemToCart(@RequestBody Items items,
							  @PathVariable("itemId") int itemId) {
//		items.setCartId(cart.getCartId());
		return itemService.addItemToCart(items,itemId,cart.getCartId());
	}
	
	@PutMapping("/update-quantity")
	@ApiOperation(
			value = "Updates the quantity of the item in the cart",
			response = Items.class
			)
	public Items updateCartItem(@RequestBody Items item) {
		return itemService.updateQuantity(item);
	}
	
	@DeleteMapping("/delete-item-by-id/{itemId}")
	@ApiOperation(
			value = "Deletes an item in the cart by item Id",
			notes = "Provide the item Id to delete the item from the cart",
			response = String.class
			)
	public String deleteItemById(@PathVariable("itemId") int itemId) {
		return itemService.deleteItemByItemId(itemId);
	}
	
	@DeleteMapping("/delete-items")
	@ApiOperation(
			value = "Deletes all the items in the cart",
			response = String.class
			)
	public String deleteCartItems() {
		return itemService.deleteAllItems();
	}
	
}
