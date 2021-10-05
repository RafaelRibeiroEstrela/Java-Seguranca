package com.example.apiuser.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiuser.models.User;
import com.example.apiuser.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Page<User>> find(Pageable pageable){
		return ResponseEntity.ok().body(userService.find(pageable));
	}
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<User>> findAll(){
		return ResponseEntity.ok().body(userService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findById(id));
	}
	
	@GetMapping(value = "/findByEmail")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		return ResponseEntity.ok().body(userService.findByEmail(email));
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		return ResponseEntity.ok().body(userService.save(user));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
		return ResponseEntity.ok().body(userService.update(id, user));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
