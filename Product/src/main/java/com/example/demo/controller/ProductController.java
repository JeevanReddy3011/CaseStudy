package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	
	@GetMapping("/getproducts")
	@ApiOperation(value="Finds all products", response = Product.class)
	public List<Product> getProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping("/addproduct")
	@ApiOperation(value="you can add product here", response = Product.class)
	public Product addProduct(@RequestBody Product product) {
		return productService.addProducts(product);
	}
	
	@GetMapping("/getbyid/{productId}")
	@ApiOperation(value="find Product by id", notes="Provide id to see the product details", response = Product.class)
	public Product getProducts(@PathVariable("productId") int productId){
		return productService.getProductById(productId);
	}
	
	@PutMapping("/updateproduct")
	@ApiOperation(value="you can update product here", response = Product.class)
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	@DeleteMapping("/deleteproduct/{productId}")
	@ApiOperation(value="you can delete product by id", response = Product.class)
	public String deleteProduct(@PathVariable("productId") int productId) {
		return productService.deleteProductById(productId);
	}


}