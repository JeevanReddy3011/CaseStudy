package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eshopping.cart.exception.DuplicateCartItemException;
import com.eshopping.cart.exception.InvalidQuantityException;
import com.eshopping.cart.exception.ProductNotFoundException;
import com.eshopping.cart.model.Cart;
import com.eshopping.cart.model.CartItems;
import com.eshopping.cart.model.Items;
import com.eshopping.cart.repository.CartRepository;
import com.eshopping.cart.repository.ItemRepository;
import com.eshopping.cart.service.CartService;
import com.eshopping.cart.service.ItemService;



@SpringBootTest
class CartMicroserviceApplicationTests {

	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CartService cartService;
	
	@MockBean
	private ItemRepository itemRepository;
	
	@MockBean 
	private CartRepository cartRepositroy;
	
	
	@Test
	void testCreateCart() {
		Cart cart = new Cart(1,1000.0);
		Mockito.when(cartRepositroy.save(cart)).thenReturn(cart);
		assertEquals(cart,cartService.createCart(cart));
	}
	
	@Test
	void testSetCreateCart() {
		Cart cart = new Cart();
		cart.setCartId(1);
		cart.setTotalAmount(1000.0);
		Mockito.when(cartRepositroy.save(cart)).thenReturn(cart);
		assertEquals(cart,cartService.createCart(cart));
	}
	
	@Test
	void testGetCartId() {
		Cart cart = new Cart(1,1000.0);
		Mockito.when(cartRepositroy.save(cart)).thenReturn(cart);
		assertEquals(1,cart.getCartId());
	}
	
	@Test
	void testGetTotalAmount() {
		Cart cart = new Cart(1,1000.0);
		Mockito.when(cartRepositroy.save(cart)).thenReturn(cart);
		assertEquals(1000.0,cart.getTotalAmount());
	}
	
	@Test
	void testAddItemToCart() {
		Items item =  new Items(1,10,"Google Pixel",60000.0,1,"In Stock","http://image-link");
//		Mockito.when(itemRepository.save(item)).thenReturn(item);
//		assertEquals(item, itemService.addItemToCart(item,10,1));
		ProductNotFoundException ex = assertThrows(
				ProductNotFoundException.class,
		           () -> itemService.addItemToCart(item,1,10)
		    );
		assertEquals("Product not found", ex.getMessage());
	}
	
	@Test
	void testDuplicateItemException() {
		Items item1 = new Items(1,10,"Google Pixel",60000.0,1,"In Stock","http://image-link");
		itemRepository.save(null);
		
		DuplicateCartItemException ex = assertThrows(
				DuplicateCartItemException.class,
		           () -> itemService.addItemToCart(item1,1,10)
		    );
		assertEquals("Item already exists in cart", ex.getMessage());
		
	}
	
	@Test
	void testinvalidQuantityException() {
		Items item =  new Items(1,10,"Google Pixel",60000.0,1,"In Stock","http://image-link");
		
		item.setQuantity(0);
		
		InvalidQuantityException ex = assertThrows(
				InvalidQuantityException.class,
		           () -> itemService.updateQuantity(item)
		    );
		assertEquals("Quantity of the item cant be less than 1.", ex.getMessage());
	}
	
	@Test
	void testGetAllCartItems() {
		Mockito.when(itemRepository.findAll()).thenReturn(Stream.of(
				new Items(1,10,"Google Pixel",60000.0,1,"In Stock","http://image-link"),
				new Items(1,20,"Pant",2000.0,1,"In Stock","http://image-link"))
				.collect(Collectors.toList()));	
	
		assertEquals(2,itemService.getAllCartItems().size());
	}
	
	@Test
	void testGetItemByItemId() {
		Items item =  new Items(1,10,"Google Pixel",60000.0,1,"In Stock","http://image-link");
		Mockito.when(itemRepository.findByItemId(1)).thenReturn(item);
		assertEquals(item,itemService.getItemByItemId(1));
	}
	
	@Test
	void testUpdateCartItem() {
		Items item =  new Items(1,10,"Google Pixel",60000.0,1,"In Stock","http://image-link");
		
		item.setProductName("One Plus 10T");
		itemRepository.save(item);
		
		assertEquals("One Plus 10T", item.getProductName());
	}
	
	@Test
	void testDeleteItemByItemId() {
		Items item =  new Items();
		item.setCartId(1);
		item.setItemId(10);
		item.setProductName("Google Pixel");
		item.setPrice(60000.0);
		item.setQuantity(1);
		assertEquals("Item with ID "+item.getItemId()+" is deleted.",
					  itemService.deleteItemByItemId(item.getItemId()));
	}
	
	@Test
	void testDeleteAllCartItems() {
		assertEquals("All items are deleted", itemService.deleteAllItems());
	}
	
	@Test
	void testTotalAmount() {
		Mockito.when(itemRepository.findAll()).thenReturn(Stream.of(
				new Items(1,10,"item1",100.0,1,"In Stock","http://image-link"),
				new Items(1,20,"item2",200.0,1,"In Stock","http://image-link"))
				.collect(Collectors.toList()));
		assertEquals(300.0, cartService.getTotalAmount());
		
	}
	
	@Test
	void testTotalAmountEmptyList() {
		itemService.deleteAllItems();
		assertEquals(0.0, cartService.getTotalAmount());
	}
	
	@Test
	void testCartItems() {
		CartItems cartItem = new CartItems();
		cartItem.setCartId(1);
		List<Items> itemsList = Arrays.asList(new Items(1,10,"item1",60000.0,1,"In Stock","http://image-link"),
											  new Items(1,10,"item2",3000.0,1,"In Stock","http://image-link"));
		cartItem.setItemList(itemsList);
		cartItem.setTotalAmount(63000.0);
		assertEquals(63000.0, cartItem.getTotalAmount());
	}	
	
	@Test
	void testGetCartItems() {
		CartItems cartItem = new CartItems();
		cartItem.setCartId(1);
		List<Items> itemsList = Arrays.asList(new Items(1,10,"item1",60000.0,1,"In Stock","http://image-link"),
											  new Items(1,10,"item2",3000.0,1,"In Stock","http://image-link"));
		cartItem.setItemList(itemsList);
		cartItem.setTotalAmount(63000.0);
		CartItems cartItem2 = new CartItems(cartItem.getCartId(), cartItem.getItemList(), 63000.0);
		assertEquals(itemsList, cartItem2.getItemList());
	}

}
