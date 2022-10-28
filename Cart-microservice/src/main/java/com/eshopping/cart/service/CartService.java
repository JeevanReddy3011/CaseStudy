package com.eshopping.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.cart.model.Cart;
import com.eshopping.cart.model.Items;
import com.eshopping.cart.repository.CartRepository;



@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemService itemService;
	
	public Cart createCart(Cart cart) {
		cart.setTotalAmount(0.0);
		return cartRepository.save(cart);
	}
	
	public double getTotalAmount() {
		double totalCost=0.0;
		List<Items> itemList = itemService.getAllCartItems();
		
		if(itemList.isEmpty()) {
			return 0.0;
		}
				
		for(Items item: itemList) {
		totalCost+=item.getQuantity() * item.getPrice();
		}
		return totalCost;
	}
	
	public String deleteCart() {
		cartRepository.deleteAll();
		return "Cart deleted";
	}
	
}
