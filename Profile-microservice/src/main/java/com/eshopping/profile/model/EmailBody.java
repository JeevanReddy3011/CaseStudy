package com.eshopping.profile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailBody {

	private String username;
	private String toMail;
	private String subject;
	private String body;
	
	
}
