package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.enums.RoleEnum;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@SpringBootApplication
public class ApiUserApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiUserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role role1 = roleRepository.save(new Role(null, RoleEnum.USER));
		Role role2 = roleRepository.save(new Role(null, RoleEnum.ADMIN));
		
		User user1 = new User(null, "carlos", "carlos@gmail.com", "12345");
		user1.getRoles().addAll(Arrays.asList(role1, role2));
		
		User user2 = new User(null, "maria", "maria@gmail.com", "sopa");
		user2.getRoles().addAll(Arrays.asList(role1));
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		
		
	}

}
