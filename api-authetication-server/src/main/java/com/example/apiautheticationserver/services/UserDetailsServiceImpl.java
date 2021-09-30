package com.example.apiautheticationserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.apiautheticationserver.models.User;
import com.example.apiautheticationserver.models.UserDetailsImpl;
import com.example.apiautheticationserver.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		System.out.println(user);
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
		return userDetailsImpl;
	}

}
