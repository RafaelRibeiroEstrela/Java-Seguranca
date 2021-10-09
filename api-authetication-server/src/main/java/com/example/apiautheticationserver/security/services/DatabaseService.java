package com.example.apiautheticationserver.security.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apiautheticationserver.security.models.Role;
import com.example.apiautheticationserver.security.models.User;
import com.example.apiautheticationserver.security.models.enums.RoleEnum;
import com.example.apiautheticationserver.security.repositories.RoleRepository;
import com.example.apiautheticationserver.security.repositories.UserRepository;

@Service
public class DatabaseService {

	@Value("${spring.profiles.active}")
	private String profile;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void makeDatabase() {
		
		if (profile.equals("test")) {
			
			Role admin = roleRepository.save(new Role(null, RoleEnum.ROLE_ADMIN));
			Role user = roleRepository.save(new Role(null, RoleEnum.ROLE_USER));
			
			User user1 = new User(null, "carlos@gmail.com", passwordEncoder.encode("12345"));
			user1.getRoles().addAll(Arrays.asList(admin, user));
			
			User user2 = new User(null, "maria@gmail.com", passwordEncoder.encode("sopa"));			
			user2.getRoles().addAll(Arrays.asList(user));
			
			userRepository.saveAll(Arrays.asList(user1, user2));
			
		}
		
		
	}
	
	
}
