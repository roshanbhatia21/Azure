package com.example.exittest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exittest.model.User;
import com.example.exittest.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		String email = user.getEmailId();
		String password = user.getPassword();

		boolean loggedIn = userService.loginUser(email, password);
		if (!loggedIn) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid credentials\"}");
		}

		return ResponseEntity.ok("{\"message\": \"Login successful\"}");
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(users);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		String email = user.getEmailId();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String password = user.getPassword();

		try {
			User registeredUser = userService.registerUser(email, firstName, lastName, password);
			return ResponseEntity.ok("{\"message\": \"Registed successful\"}");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error registering\"}");
		}
	}

	@GetMapping("/users/count")
	public ResponseEntity<Long> countUsers() {
		long count = userService.countUsers();
		return ResponseEntity.ok(count);
	}

}
