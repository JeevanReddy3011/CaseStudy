package com.eshopping.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eshopping.profile.exception.DuplicateUsernameException;
import com.eshopping.profile.exception.InvalidEmailFormatException;
import com.eshopping.profile.exception.InvalidPasswordFormatException;
import com.eshopping.profile.exception.InvalidUsernameFormatException;
import com.eshopping.profile.model.User;
import com.eshopping.profile.repository.UserRepository;
import com.eshopping.profile.service.CustomUserDetailsService;
import com.eshopping.profile.service.UserService;

@SpringBootTest
class ProfileMicroserviceApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	private UserRepository userRepository;
	
	
//	@Test
//	void testRegisterValidUser() {
//		User user = new User(
//				1,
//				"test name",
//				"http://sample-image",
//				"test",
//				"test@mail.com",
//				"Testtest1!",
//				1111111111,
//				"test address",
//				"test about",
//				"21-08-2022",
//				"male",
//				"customer");
//		
//		Mockito.when(userRepository.save(user)).thenReturn(user);
//		assertEquals("User successfully registered", userService.registerUser(user));
//	}
//	
//	@Test
//	void testSetRegisterUser() {
//		User user = new User();
//		
//		user.setUserId(1);
//		user.setFullName("test name");
//		user.setImage("http://sample-image");
//		user.setUsername("test");
//		user.setEmailId("test@mail.com");
//		user.setPassword("Testtest1!");
//		user.setMobileNumber(1111111111);
//		user.setAddress("test address");
//		user.setAbout("test about");
//		user.setDob("21-08-2022");
//		user.setGender("male");
//		user.setRole("customer");
//		
//		Mockito.when(userRepository.save(user)).thenReturn(user);
//		assertEquals("User successfully registered", userService.registerUser(user));
//	}
	
	@Test
	void testpasswordEncoder() {
		User user = new User(
			1,
			"test name",
			"http://sample-image",
			"test",
			"test@mail.com",
			"Testtest1!",
			1111111111,
			"test address",
			"test about",
			"21-08-2022",
			"male",
			"customer");
		String encodedPassword = bCryptPasswordEncoder.encode("password");
		user.setPassword(encodedPassword);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		assertEquals(encodedPassword, user.getPassword());
	}
	
	@Test
	void testEmailException() {
		User user = new User(
				1,
				"test name",
				"http://sample-image",
				"test",
				"!test@mail.com",
				"Testtest1!",
				1111111111,
				"test address",
				"test about",
				"21-08-2022",
				"male",
				"customer");
		
		InvalidEmailFormatException ex = assertThrows(
				InvalidEmailFormatException.class,
		           () -> userService.registerUser(user)
		    );
		assertEquals("Invalid email format.", ex.getMessage());
	}
	
	@Test
	void testPasswordException() {
		User user = new User(
				1,
				"test name",
				"http://sample-image",
				"test",
				"test@mail.com",
				"Test",
				1111111111,
				"test address",
				"test about",
				"21-08-2022",
				"male",
				"customer");
		
		InvalidPasswordFormatException ex = assertThrows(
				InvalidPasswordFormatException.class,
		           () -> userService.registerUser(user)
		    );
		assertEquals("Invalid password format.", ex.getMessage());
	}
	
	@Test
	void testUsernameException() {
		User user = new User(
				1,
				"test name",
				"http://sample-image",
				"te!",
				"test@mail.com",
				"Testtest1!",
				1111111111,
				"test address",
				"test about",
				"21-08-2022",
				"male",
				"customer");
		
		InvalidUsernameFormatException ex = assertThrows(
				InvalidUsernameFormatException.class,
		           () -> userService.registerUser(user)
		    );
		assertEquals("Invalid username format.", ex.getMessage());
	}
	
	@Test
	void testFindByUsername() {
		User user = new User(
				1,
				"test name",
				"http://sample-image",
				"test",
				"test@mail.com",
				"Testtest1!",
				1111111111,
				"test address",
				"test about",
				"21-08-2022",
				"male",
				"customer");
		Mockito.when(userRepository.findByUsername("test")).thenReturn(user);
		assertEquals(user,userService.getUserByUsername("test"));
	}
	
	@Test
	void testUserNameNotfoundException() {
		User user = new User(
				1,
				"test name",
				"http://sample-image",
				"test",
				"test@mail.com",
				"Testtest1!",
				1111111111,
				"test address",
				"test about",
				"21-08-2022",
				"male",
				"customer");
		
		Mockito.when(userRepository.findByUsername("test")).thenReturn(user);
		
		String username = "test1";
		
		UsernameNotFoundException ex = assertThrows(
				UsernameNotFoundException.class,
		           () -> customUserDetailsService.loadUserByUsername(username)
		    );
		assertEquals("No user with username : "+username+" found.", ex.getMessage());
	}
	
	@Test
	void testDuplicateUsernameException() {
		Mockito.when(userRepository.findAll()).thenReturn(Stream.of(
				   new User(
							1,
							"test name",
							"http://sample-image",
							"test",
							"test@mail.com",
							"Testtest1!",
							1111111111,
							"test address",
							"test about",
							"21-08-2022",
							"male",
							"customer"),
					new User(
							2,
							"test name2",
							"http://sample-image2",
							"test2",
							"test2@mail.com",
							"Testtest2!",
							1111111112,
							"test address2",
							"test about2",
							"22-08-2022",
							"female",
							"merchant"))
					.collect(Collectors.toList()));
		
		User user3 = new User(
				2,
				"test name2",
				"http://sample-image2",
				"test2",
				"test2@mail.com",
				"Testtest2!",
				1111111112,
				"test address2",
				"test about2",
				"22-08-2022",
				"female",
				"merchant"
				);
		
		DuplicateUsernameException ex = assertThrows(
				DuplicateUsernameException.class,
		           () -> userService.registerUser(user3)
		    );
		assertEquals("Username already exists please try again", ex.getMessage());
	}

	@Test
	void testGetAllUsers() {
		Mockito.when(userRepository.findAll()).thenReturn(Stream.of(
			   new User(
						1,
						"test name",
						"http://sample-image",
						"test",
						"test@mail.com",
						"Testtest1!",
						1111111111,
						"test address",
						"test about",
						"21-08-2022",
						"male",
						"customer"),
				new User(
						2,
						"test name2",
						"http://sample-image2",
						"test2",
						"test2@mail.com",
						"Testtest2!",
						1111111112,
						"test address2",
						"test about2",
						"22-08-2022",
						"female",
						"merchant"))
				.collect(Collectors.toList()));	
	
		assertEquals(2,userService.getAllUsers().size());
	}

	@Test
	void testGetUserByUserId() {
		User user = new User(
				1,
				"test name",
				"http://sample-image",
				"test",
				"test@mail.com",
				"test",
				1111111111,
				"test address",
				"test about",
				"21-08-2022",
				"male",
				"customer");
		User anotherUser = new User(
				2,
				"test name",
				user.getImage(),
				user.getUsername(),
				user.getEmailId(),
				user.getPassword(),
				1111111111,
				user.getAddress(),
				user.getAbout(),
				user.getDob(),
				user.getGender(),
				user.getRole()
				);
		Mockito.when(userRepository.findByUserId(2)).thenReturn(anotherUser);
		assertEquals(anotherUser, userService.getUserByUserId(2));
	}
	
	@Test
	void testUpdateUser() {
		User user = new User(
				1,
				"test name",
				"http://sample-image",
				"test",
				"test@mail.com",
				"test",
				1111111111,
				"test address",
				"test about",
				"21-08-2022",
				"male",
				"customer");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		user.setFullName("test name1");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertEquals("test name1", user.getFullName());	
	}
	
	@Test
	void testDeleteUserByid() {
		User user = new User(
				1,
				"test name",
				"http://sample-image",
				"test",
				"test@mail.com",
				"test",
				1111111111,
				"test address",
				"test about",
				"21-08-2022",
				"male",
				"customer");
		assertEquals("user profile with ID "+user.getUserId()+" is deleted.",
					 userService.deleteUserByUserId(1));
	}
	
	@Test 
	void testDeleteAllUsers(){
		assertEquals("All user profiles are deleted.", userService.deleteAllUsers());
	}
}
