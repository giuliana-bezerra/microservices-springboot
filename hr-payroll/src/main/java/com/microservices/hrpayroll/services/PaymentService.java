package com.microservices.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.hrpayroll.entities.Payment;
import com.microservices.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String host;

	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {
		Map<String, String> uriVars = new HashMap<>();
		uriVars.put("id", workerId.toString());
		
		String url = host + "/workers/{id}";

		Worker worker = restTemplate.getForObject(url , Worker.class, uriVars);
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
