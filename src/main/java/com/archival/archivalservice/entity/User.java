package com.archival.archivalservice.entity;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.archival.archivalservice.dto.Role;

@Entity
public class User {
	
	public User() {
		
	}
	
	@Id
	private String username;

	private String password;

	@ElementCollection(targetClass = Role.class)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
