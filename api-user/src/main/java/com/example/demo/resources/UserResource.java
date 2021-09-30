package com.example.demo.resources;

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

import com.example.demo.models.User;
import com.example.demo.services.UserService;

import javassist.tools.rmi.ObjectNotFoundException;

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
	public ResponseEntity<User> findById(Long id) throws ObjectNotFoundException{
		return ResponseEntity.ok().body(userService.findById(id));
	}
	
	@GetMapping(value = "/findByEmail")
	public ResponseEntity<User> findByEmail(@RequestParam String email) throws ObjectNotFoundException{
		return ResponseEntity.ok().body(userService.findByEmail(email));
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) throws ObjectNotFoundException{
		return ResponseEntity.created(null).body(userService.save(user));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) throws ObjectNotFoundException{
		return ResponseEntity.ok().body(userService.update(id, user));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(Long id) throws ObjectNotFoundException{
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
