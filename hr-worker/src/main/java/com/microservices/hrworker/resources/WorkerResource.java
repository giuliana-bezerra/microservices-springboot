package com.microservices.hrworker.resources;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.hrworker.entities.Worker;
import com.microservices.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	@Autowired
	private WorkerRepository repository;

	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> workers = repository.findAll();
		return ResponseEntity.ok(workers);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		try {
			Worker worker = repository.findById(id).get();
			return ResponseEntity.ok(worker);
		} catch (NoSuchElementException exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
