package com.eshopping.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eshopping.profile.model.CustomUserDetails;
import com.eshopping.profile.model.User;
import com.eshopping.profile.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("No user with username : "+username+" found.");
		}
		return new CustomUserDetails(user);
	}

}
//