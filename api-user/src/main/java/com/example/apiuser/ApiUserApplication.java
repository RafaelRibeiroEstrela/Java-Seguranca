package com.example.apiuser;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.apiuser.models.Role;
import com.example.apiuser.models.User;
import com.example.apiuser.models.enums.RoleEnum;
import com.example.apiuser.repositories.RoleRepository;
import com.example.apiuser.repositories.UserRepository;

@SpringBootApplication
public class ApiUserApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApiUserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role admin = roleRepository.save(new Role(null, RoleEnum.ADMIN));
		Role user = roleRepository.save(new Role(null, RoleEnum.USER));
		roleRepository.saveAll(Arrays.asList(admin, user));
		
		User user1 = new User(null, "Carlos", "carlos@gmail.com", passwordEncoder.encode("12345"));
		user1.getRoles().addAll(Arrays.asList(admin, user));
		
		User user2 = new User(null, "Maria", "maria@gmail.com", passwordEncoder.encode("sopa"));
		user2.getRoles().addAll(Arrays.asList(user));
		
		userRepository.saveAll(Arrays.asList(user1, user2));
	}

}
