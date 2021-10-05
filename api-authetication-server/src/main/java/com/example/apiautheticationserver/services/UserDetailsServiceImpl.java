package com.example.apiautheticationserver.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.apiautheticationserver.models.User;
import com.example.apiautheticationserver.models.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = getUserWithRestTemplate(email);
		System.out.println(user);
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
		return userDetailsImpl;
	}
	
	private User getUserWithRestTemplate(String email) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://localhost:8080/users/findByEmail?email=" + email, User.class);
	}

}
