package com.eshopping.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.profile.model.Address;
import com.eshopping.profile.model.AuthenticationRequest;
import com.eshopping.profile.model.AuthenticationResponse;
import com.eshopping.profile.model.User;
import com.eshopping.profile.model.UserInfo;
import com.eshopping.profile.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(
			value = "registers the users",
			response = String.class
			)
	
	@PostMapping("/register-user")
	public String registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@ApiOperation(
			value = "Used for users to log in",
			response = AuthenticationResponse.class
			)
	
	@PostMapping("/login")
	public AuthenticationResponse getLoginMessage(@RequestBody AuthenticationRequest authenticationRequest) {
		return userService.login(authenticationRequest);
	}
	
	
	@ApiOperation(
			value = "Adds user address",
			response = Address.class
			)
	
	@PostMapping("/add-address")
	public Address addAddress(@RequestBody Address address) {
		return userService.addUserAddress(address);
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "<h1>Welcome to EShopping Zone</h1>";
	}
	
	@ApiOperation(
			value = "Gets all the user details",
			response = List.class
			)
	
	@GetMapping("/get-all-user")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@ApiOperation(
			value = "Gets the user details along with their addresses",
			notes = "Provide an user Id to get the user details and addresses of a specific user",
			response = UserInfo.class
			)
	
	@GetMapping("/get-userInfo-by-id/{userId}")
	public UserInfo getUserInfoByid(@PathVariable("userId") int userId) {
		User user = userService.getUserByUserId(userId);
		List<Address> addressList = userService.getAddressByUserId(userId);
		return new UserInfo(user,addressList);
	}
	
	@ApiOperation(
			value = "Gets user details only",
			response = User.class
			)
	
	@GetMapping("/get-user-by-id/{userId}")
	public User getUserByid(@PathVariable("userId") int userId) {
		return userService.getUserByUserId(userId);
	}
	
	@ApiOperation(
			value = "Gets user details by username",
			response = User.class
			)
	
	@GetMapping("/get-user-by-username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
	
	@ApiOperation(
			value = "Gets all the addresses for the user",
			notes = "Provide an user Id to get the addresses of the user",
			response = List.class
			)
	
	@GetMapping("/get-address-by-userId/{userId}")
	public List<Address> getAddresses(@PathVariable("userId") int userId){
		return userService.getAddressByUserId(userId);
	}
	
	@ApiOperation(
			value = "updates the user details",
			response = User.class
			)
	
	@PutMapping("/update-user")
	public User updateUserDetails(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@ApiOperation(
			value = "Deletes user by user ID",
			notes = "Provide an user Id to delete the user",
			response = String.class
			)
	
	@DeleteMapping("/delete-by-userId/{userId}")
	public String deleteUserById(@PathVariable("userId") int userId) {
		return userService.deleteUserByUserId(userId);
	}
	
	@ApiOperation(
			value = "Deletes all the user details",
			response = String.class
			)
	
	@DeleteMapping("/delete-users")
	public String deleteAllUser() {
		return userService.deleteAllUsers();
	}
}
