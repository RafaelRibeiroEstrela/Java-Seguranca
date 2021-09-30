package com.example.apiautheticationserver;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.apiautheticationserver.models.Role;
import com.example.apiautheticationserver.models.User;
import com.example.apiautheticationserver.models.enums.RoleEnum;
import com.example.apiautheticationserver.repositories.RoleRepository;
import com.example.apiautheticationserver.repositories.UserRepository;

@SpringBootApplication
public class ApiAutheticationServerApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiAutheticationServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role role1 = roleRepository.save(new Role(null, RoleEnum.USER));
		Role role2 = roleRepository.save(new Role(null, RoleEnum.ADMIN));
		
		User user1 = new User(null, "carlos", "carlos@gmail.com", passwordEncoder.encode("12345"));
		user1.getRoles().addAll(Arrays.asList(role1, role2));
		
		User user2 = new User(null, "maria", "maria@gmail.com", passwordEncoder.encode("sopa"));
		user2.getRoles().addAll(Arrays.asList(role1));
		
		User user3 = new User(null, "rafael", "rafael@gmail.com", passwordEncoder.encode("treta"));
		user3.getRoles().addAll(Arrays.asList(role2));
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
	}

}
