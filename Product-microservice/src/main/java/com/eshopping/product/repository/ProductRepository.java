package com.eshopping.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Product findByProductName(String productName);
	List<Product> findAllByProductType(String productType);
	Product findByProductId(int productId);
}
