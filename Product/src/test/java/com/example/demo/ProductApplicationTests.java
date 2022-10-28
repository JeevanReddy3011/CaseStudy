package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@SpringBootTest
class ProductApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepository;
	
	
	@Test
	void testAddProduct() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		Mockito.when(productRepository.save(product)).thenReturn(product);
		assertEquals(product, productService.addProducts(product));
	}
	
	@Test
	void testGetAllProducts() {
		Mockito.when(productRepository.findAll()).thenReturn(Stream.of(
				new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img"),
				new Product(1,"Pant",2000.0,1,"jeans","Jeans","img"))
				.collect(Collectors.toList()));	
	
		assertEquals(2,productService.getAllProducts().size());
	}
	
	
	@Test
	void testUpdateProduct() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		productRepository.save(product);
		product.setProductName("One Plus 10T");
		productRepository.save(product);
		
		assertEquals("One Plus 10T", product.getProductName());
	}
	@Test
	void testUpdateImage() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		productRepository.save(product);
		product.setImage("imageofphone");
		productRepository.save(product);
		
		assertEquals("imageofphone", product.getImage());
	}
	@Test
	void testUpdateProductId() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		productRepository.save(product);
		product.setProductId(2);
		productRepository.save(product);
		
		assertEquals(2, product.getProductId());
	}
	@Test
	void testUpdateProductPrice() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		productRepository.save(product);
		product.setPrice(70000.0);
		productRepository.save(product);
		assertEquals(70000.0, product.getPrice());
	}
	@Test
	void testUpdateQuantity() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		productRepository.save(product);
		product.setQuantity(10);
		productRepository.save(product);
		
		assertEquals(10, product.getQuantity());
	}
	@Test
	void testUpdateProductType() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		productRepository.save(product);
		product.setProductType("Mobilephone");
		productRepository.save(product);
		
		assertEquals("Mobilephone", product.getProductType());
	}
	@Test
	void testUpdateProductDescription() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		productRepository.save(product);
		product.setDescription("a good one");
		productRepository.save(product);
		
		assertEquals("a good one", product.getDescription());
	}
	@Test
	void testDeleteAllCartproducts() {
		assertEquals("All products are deleted", productService.deleteAllProducts());
	}
	
	@Test
	void testfindByProductId() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		Mockito.when(productRepository.findByProductId(1)).thenReturn(product);
		assertEquals(product, productService.getProductById(1));
	}
	
	@Test
	void testdeleteProductById() {
		Product product =  new Product(1,"Google Pixel",60000.0,1,"mobile","a mobile","img");
		assertEquals("Product deleted", productService.deleteProductById(1));
	}
	

}