package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<User>> findAll(){
		return ResponseEntity.ok().body(userService.findAll());
	}
	
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findById(id));
	}
	
	@GetMapping(value = "/findByEmail/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email){
		return ResponseEntity.ok().body(userService.findByEmail(email));
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<User> save(@RequestBody User user){
		return ResponseEntity.created(null).body(userService.save(user));
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
		return ResponseEntity.ok().body(userService.update(id, user));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.ok().body(null);
	}
	
}
