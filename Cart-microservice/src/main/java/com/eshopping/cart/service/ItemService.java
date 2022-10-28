package com.eshopping.cart.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eshopping.cart.exception.DuplicateCartItemException;
import com.eshopping.cart.exception.InvalidQuantityException;
import com.eshopping.cart.exception.ProductNotFoundException;
import com.eshopping.cart.model.Items;
import com.eshopping.cart.model.Product;
import com.eshopping.cart.repository.ItemRepository;



@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String PRODUCT_URL = "http://PRODUCT-MICROSERVICE/product/";
	
	
	public List<Items> getAllCartItems() {
		return itemRepository.findAll();
	}
	
	public Items getItemByItemId(int itemId) {
		return itemRepository.findByItemId(itemId);
	}
	
	public Items addItemToCart(Items item,int itemId,int cartId) {
		
		Product product = restTemplate.getForObject(PRODUCT_URL+"getbyid/"+itemId,Product.class);
		
		if(product==null) {
			throw new ProductNotFoundException("Product not found");
		}
		
		itemRepository.findAll().stream().forEach(items->{
			if(items.getItemId()==itemId) {
				throw new DuplicateCartItemException("Item already exists in cart");
			}
		});
		
		item.setCartId(cartId);
		item.setItemId(itemId);
		item.setProductName(product.getProductName());
		item.setPrice(product.getPrice());
		item.setQuantity(1);
		item.setAvailabilityStatus(product.getAvailabilityStatus());
		item.setImage(product.getImage());
		
		return itemRepository.save(item);
	}
	
	public Items updateQuantity(Items item) {
		if(item.getQuantity() < 1) {
			throw new InvalidQuantityException("Quantity of the item cant be less than 1.");
		}
		return itemRepository.save(item);
	}

	
	public String deleteItemByItemId(int itemId) {
		 itemRepository.deleteById(itemId);
		 return "Item with ID "+itemId+" is deleted.";
	}
	
	public String deleteAllItems() {
		itemRepository.deleteAll();
		return "All items are deleted";
	}
}
