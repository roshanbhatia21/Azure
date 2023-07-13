package com.example.exittest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exittest.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmailId(String emailId);
}
