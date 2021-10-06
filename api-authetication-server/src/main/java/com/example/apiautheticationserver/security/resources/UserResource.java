package com.example.apiautheticationserver.security.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiautheticationserver.security.models.User;
import com.example.apiautheticationserver.security.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Page<User>> find(Pageable pageable){
		return ResponseEntity.ok().body(userService.find(pageable));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findById(id));
	}
	
	@GetMapping(value = "/findByUsername")
	public ResponseEntity<User> findByUsername(@RequestParam String username){
		return ResponseEntity.ok().body(userService.findByUsername(username));
	}
	
	
	
}
