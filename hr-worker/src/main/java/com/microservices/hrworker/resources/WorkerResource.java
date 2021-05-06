package com.microservices.hrworker.resources;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.hrworker.entities.Worker;
import com.microservices.hrworker.repositories.WorkerRepository;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
  private static final Logger logger = LoggerFactory.getLogger(WorkerResource.class);

//	@Value("${test.config}")
//	private String testConfig;

  @Autowired
  private Environment env;

  @Autowired
  private WorkerRepository repository;

  /** Testing config server */
//	@GetMapping(value = "/config")
//	public ResponseEntity<Void> getConfig() {
//		logger.info("CONFIG = " + testConfig);
//		return ResponseEntity.noContent().build();
//	}

  @GetMapping
  public ResponseEntity<List<Worker>> findAll() {
    List<Worker> workers = repository.findAll();
    return ResponseEntity.ok(workers);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Worker> findById(@PathVariable Long id) {
    logger.info("PORT = " + env.getProperty("local.server.port"));

    try {
      Worker worker = repository.findById(id).get();
      return ResponseEntity.ok(worker);
    } catch (NoSuchElementException exception) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(value = "/hystrix")
  public ResponseEntity<String> hystrix() {
    try {
      Thread.sleep(5000L);
      return ResponseEntity.noContent().build();
    } catch (InterruptedException e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body("Timeout do Hystrix!");
    }
  }
}
