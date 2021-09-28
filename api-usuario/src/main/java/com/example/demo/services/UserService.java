package com.example.demo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not find by id = " + id));
	}
	
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not find by email = " + email);
		}
		return user;
	}
	
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User update(Long id, User user) {
		User userDb = findById(id);
		BeanUtils.copyProperties(user, userDb, "id", "password", "roles");
		return userRepository.save(userDb);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	
}
