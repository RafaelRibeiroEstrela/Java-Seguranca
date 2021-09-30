package com.example.apiautheticationserver.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	private Set<Role> roles = new HashSet<>();
	
	public UserDetailsImpl(User user) {
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		for (Role role : roles) {
			grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleEnum().getDesc()));
		}
		return grantedAuthorityList;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
