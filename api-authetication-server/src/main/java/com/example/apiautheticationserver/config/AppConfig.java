package com.example.apiautheticationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.example.apiautheticationserver.security.repositories.RoleRepository;
import com.example.apiautheticationserver.security.repositories.UserRepository;

@Configuration
public class AppConfig {

	@Value("${spring.profiles.active}")
	private String profile;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void makeDatabase() {
		
		if (profile.equals("test")) {
			
			//CRIAR O SCRIPT
			
			
			
		}
		
		
	}
	
	
}
