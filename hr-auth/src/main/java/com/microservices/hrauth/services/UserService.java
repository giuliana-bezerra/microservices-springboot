package com.microservices.hrauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservices.hrauth.entities.User;
import com.microservices.hrauth.feignclients.UserFeignClient;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserFeignClient client;

	public User findByEmail(String email) {
		User user = client.findByEmail(email).getBody();
		if (user == null)
			throw new IllegalArgumentException("Email not found!");
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = client.findByEmail(username).getBody();
		if (user == null)
			throw new UsernameNotFoundException("Email not found!");
		return user;
	}
}
