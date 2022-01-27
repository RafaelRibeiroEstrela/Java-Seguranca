package com.example.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
