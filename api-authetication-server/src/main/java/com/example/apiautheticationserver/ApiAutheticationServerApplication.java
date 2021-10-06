package com.example.apiautheticationserver;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.apiautheticationserver.security.models.Role;
import com.example.apiautheticationserver.security.models.User;
import com.example.apiautheticationserver.security.models.enums.RoleEnum;
import com.example.apiautheticationserver.security.repositories.RoleRepository;
import com.example.apiautheticationserver.security.repositories.UserRepository;

@SpringBootApplication
public class ApiAutheticationServerApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiAutheticationServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role admin = roleRepository.save(new Role(null, RoleEnum.ROLE_ADMIN));
		Role user = roleRepository.save(new Role(null, RoleEnum.ROLE_USER));
		
		User user1 = new User(null, "carlos@gmail.com", passwordEncoder.encode("12345"));
		user1.getRoles().addAll(Arrays.asList(admin, user));
		
		userRepository.saveAll(Arrays.asList(user1));
		
		
	}

}
