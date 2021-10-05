package com.example.apiuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiuser.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);

}
