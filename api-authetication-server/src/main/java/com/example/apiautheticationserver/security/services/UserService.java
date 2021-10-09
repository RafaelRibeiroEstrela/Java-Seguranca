package com.example.apiautheticationserver.security.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apiautheticationserver.security.models.User;
import com.example.apiautheticationserver.security.repositories.UserRepository;
import com.example.apiautheticationserver.security.services.exceptions.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public Page<User> find(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found by id = " + id));
	}
	
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new EntityNotFoundException("User not found by username = " + username);
		}
		return user;
	}
	
	public User save(User user) {
		user.setPassword(encriptyPassword(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User update(Long id, User user) {
		User userDb = findById(id);
		if (!user.getPassword().equals(userDb.getPassword())) {
			user.setPassword(encriptyPassword(user.getPassword()));
		}
		
		BeanUtils.copyProperties(user, userDb, "id");
		return userRepository.save(userDb);
	}
	
	public void delete(Long id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	private String encriptyPassword(String password) {
		return passwordEncoder.encode(password);
	}
	

}
