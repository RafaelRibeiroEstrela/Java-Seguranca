package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@SpringBootApplication
public class ApiUsuarioApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiUsuarioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		User user1 = new User(null, "Carlos", "carlos@gmail.com", passwordEncoder.encode("12345"));
		User user2 = new User(null, "Maria", "maria@gmail.com", passwordEncoder.encode("sopa"));
		
		Role role1 = roleRepository.save(new Role(null, "OPERATOR"));
		Role role2 = roleRepository.save(new Role(null, "ADMIN"));
		
		user1.getRoles().addAll(Arrays.asList(role1, role2));
		user2.getRoles().addAll(Arrays.asList(role1));
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		
		
	}

}
