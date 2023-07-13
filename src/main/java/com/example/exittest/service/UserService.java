package com.example.exittest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exittest.model.User;
import com.example.exittest.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Authenticates a user by checking the email and password.
	 *
	 * @param email    the email of the user
	 * @param password the password of the user
	 * @return true if the login is successful, false otherwise
	 */
	public boolean loginUser(String email, String password) {
		User user = userRepository.findByEmailId(email);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	/**
	 * Retrieves all users.
	 *
	 * @return a list of all users
	 */
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Registers a new user.
	 *
	 * @param email     the email of the user
	 * @param firstName the first name of the user
	 * @param lastName  the last name of the user
	 * @param password  the password of the user
	 * @return the registered user
	 * @throws IllegalArgumentException if a user with the given email already
	 *                                  exists
	 */
	public User registerUser(String email, String firstName, String lastName, String password) {
		User existingUser = userRepository.findByEmailId(email);
		if (existingUser != null) {
			throw new IllegalArgumentException("User with this email already exists");
		}

		User newUser = new User();
		newUser.setEmailId(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setPassword(password);

		return userRepository.save(newUser);
	}

	/**
	 * Counts the number of users.
	 *
	 * @return the total number of users
	 */
	public long countUsers() {
		return userRepository.count();
	}
}
