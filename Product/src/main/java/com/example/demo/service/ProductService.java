package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public Product addProducts(Product product) {
		return productRepository.save(product);
		
	}
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Product getProductById(int productId) {
		return productRepository.findByProductId(productId);
	}
	
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}
	

	public String deleteProductById(int productId) {
		productRepository.deleteById(productId);
		return "Product deleted";
	}
	
	public String deleteAllProducts() {
		productRepository.deleteAll();
		return "All products are deleted";
	}


	

}