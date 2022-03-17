package com.example.security.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.example.security.models.User;
import com.example.security.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Page<User>> find(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findPageable(pageable));
	}
	
	@GetMapping(value = "find-all")
	public ResponseEntity<List<User>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}
	
	@GetMapping(value = "/find-username")
	public ResponseEntity<User> findByUsername(@RequestParam String username){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsername(username));
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid User user){
		return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
