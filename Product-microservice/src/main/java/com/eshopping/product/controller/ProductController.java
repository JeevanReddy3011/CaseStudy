package com.eshopping.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eshopping.product.model.Product;
import com.eshopping.product.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;

	HttpHeaders headers = new HttpHeaders();
	
	@GetMapping("/getproducts")
	public List<Product> getProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/get-products-by-type/{productType}")
	public List<Product> getByProductType(@PathVariable("productType") String productType){
		return productService.getAllProductsByProductType(productType);
	}
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		
		return productService.addProducts(product);
	}
	
	@GetMapping("/getbyid/{productId}")
	public Product getProducts(@PathVariable("productId") int productId){
		 return productService.getProductById(productId);
	}
	
	@PutMapping("/updateproduct")
	public Product updateProduct(@RequestBody Product product) { 
		
		return productService.updateProduct(product);
	}
	
	@DeleteMapping("/deleteproduct/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId) {
		
		return productService.deleteProductById(productId);
	}

	@DeleteMapping("/deleteproducts")
	public String deleteProducts() {
		
		return productService.deleteAllProducts();
	}
	
}
