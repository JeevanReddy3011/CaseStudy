package com.eshopping.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.product.model.Product;
import com.eshopping.product.repository.ProductRepository;


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
	
	public List<Product> getAllProductsByProductType(String productType) {
		return productRepository.findAllByProductType(productType);
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
