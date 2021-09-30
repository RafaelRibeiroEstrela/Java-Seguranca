package com.example.demo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Page<User> find(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) throws ObjectNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found by id = " + id));
	}
	
	public User findByEmail(String email) throws ObjectNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ObjectNotFoundException("User not found by email = " + email);
		}
		return user;
	}
	
	public User save(User user) throws ObjectNotFoundException {
		findRole(user);
		return userRepository.save(user);
	}
	
	public User update(Long id, User user) throws ObjectNotFoundException {
		User userDb = findById(id);
		findRole(user);
		BeanUtils.copyProperties(user, userDb, "id");
		return userRepository.save(userDb);
	}
	
	public void delete(Long id) throws ObjectNotFoundException {
		findById(id);
		userRepository.deleteById(id);
	}
	
	private void findRole(User user) throws ObjectNotFoundException {
		for (Role role : user.getRoles()) {
			Long id = role.getId();
			role = roleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Role not found by id = " + id));
		}
	}
	

}
