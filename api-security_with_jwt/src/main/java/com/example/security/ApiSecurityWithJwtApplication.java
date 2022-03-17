package com.example.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.security.models.Role;
import com.example.security.models.enums.RoleEnum;
import com.example.security.repositories.RoleRepository;

@SpringBootApplication
public class ApiSecurityWithJwtApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiSecurityWithJwtApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Role admin = new Role(null, RoleEnum.ROLE_ADMIN);
		Role user = new Role(null, RoleEnum.ROLE_USER);
		
		roleRepository.saveAll(Arrays.asList(admin, user));
		
	}

}
