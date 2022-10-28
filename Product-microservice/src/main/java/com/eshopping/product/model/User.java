package com.eshopping.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int userId;
	private String fullName;
	private String image;
	private String username;
	private String emailId;
	private String password;
	private long mobileNumber;
	private String address;
	private String about;
	private String dob;
	private String gender;
	private String role;

}
