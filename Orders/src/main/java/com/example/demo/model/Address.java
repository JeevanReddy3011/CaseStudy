package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Address {

	private int addressId;
	private int userId;
	private String locality;
	private String city;
	private String state;
	private String country;
	private long pincode;
	
}
