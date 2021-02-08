package com.microservices.hrauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HrAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrAuthApplication.class, args);
	}

}
