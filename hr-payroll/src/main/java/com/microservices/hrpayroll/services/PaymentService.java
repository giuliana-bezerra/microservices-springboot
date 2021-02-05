package com.microservices.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.hrpayroll.entities.Payment;
import com.microservices.hrpayroll.entities.Worker;
import com.microservices.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	@Autowired
	private WorkerFeignClient client;

	public Payment getPayment(Long workerId, Integer days) {
		Worker worker = client.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
