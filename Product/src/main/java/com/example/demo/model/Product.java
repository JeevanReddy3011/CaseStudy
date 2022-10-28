package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;



@Entity
public class Product {
	
	@Id
	@ApiModelProperty(notes="The unique Id of contact")
	private int productId;
	@ApiModelProperty(notes="the name of product")
	private String productName;
	@ApiModelProperty(notes="Price of product")
	private double price;
	@ApiModelProperty(notes="available")
	private int quantity;
	private String productType;
	private String description;
	private String image;
	
	public Product(int productId, String productName, double price, int quantity, String productType,
			String description, String image) {
		
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.productType = productType;
		this.description = description;
		this.setImage(image);
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Product() {
		
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}