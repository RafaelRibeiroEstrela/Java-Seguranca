package com.example.apiautheticationserver.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apiautheticationserver.models.Role;
import com.example.apiautheticationserver.models.User;
import com.example.apiautheticationserver.repositories.RoleRepository;
import com.example.apiautheticationserver.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public Page<User> find(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found by id = " + id));
	}
	
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found by email = " + email);
		}
		return user;
	}
	
	public User save(User user) {
		findRole(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User update(Long id, User user) {
		User userDb = findById(id);
		if (user.getPassword() != userDb.getPassword()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}	
		findRole(user);
		BeanUtils.copyProperties(user, userDb, "id");
		return userRepository.save(userDb);
	}
	
	public void delete(Long id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	private void findRole(User user) {
		if (!user.getRoles().isEmpty() && user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				Long id = role.getId();
				role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found by id = " + id));
			}
		}else {
			throw new IllegalStateException("List of roles can't empty");
		}
		
	}
	

}
