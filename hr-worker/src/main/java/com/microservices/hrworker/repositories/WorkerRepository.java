package com.microservices.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
