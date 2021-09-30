package com.example.apiautheticationserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiautheticationserver.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);

}
