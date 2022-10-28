package com.eshopping.profile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue
	private int userId;
	private String fullName;
	private String image;
	private String username;
	private String emailId;
	private String password;
	private long mobileNumber;
	private String about;
	private String dob;
	private String gender;
	private String role;

}
