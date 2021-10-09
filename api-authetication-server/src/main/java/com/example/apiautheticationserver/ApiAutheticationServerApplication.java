package com.example.apiautheticationserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.apiautheticationserver.security.services.DatabaseService;

@SpringBootApplication
public class ApiAutheticationServerApplication implements CommandLineRunner{
	
	@Autowired
	private DatabaseService databaseService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiAutheticationServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		databaseService.makeDatabase();
	}

}
