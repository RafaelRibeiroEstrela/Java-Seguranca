package com.example.apiautheticationserver.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.apiautheticationserver.security.models.User;
import com.example.apiautheticationserver.security.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<User> find(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found by id = " + id));
	}
	
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("User not found by username = " + username);
		}
		return user;
	}
	
	

}
