package com.microservices.hrauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.hrauth.entities.User;
import com.microservices.hrauth.services.UserService;

import feign.FeignException;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<User> findByEmail(@RequestParam(required = true) String email) {
		try {
			User user = service.findByEmail(email);
			return ResponseEntity.ok(user);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		} catch (FeignException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
