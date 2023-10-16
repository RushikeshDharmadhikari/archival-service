package com.archival.archivalservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.archival.archivalservice.entity.ArchivalDeletionStrategy;
import com.archival.archivalservice.entity.ArchivalStrategy;
import com.archival.archivalservice.repository.ArchivalDeletionStrategyRepository;
import com.archival.archivalservice.repository.ArchivalStrategyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ArchivalStrategyService {

	@Autowired
	ArchivalStrategyRepository archivalStrategyRepository;

	@Autowired
	ArchivalDeletionStrategyRepository archivalDeletionStrategyRepository;

	public ArchivalStrategy createArchivalStrategy(ArchivalStrategy archivalStrategy) {
		return archivalStrategyRepository.save(archivalStrategy);
	}

	public List<ArchivalStrategy> getAllArchivalStrategies() {
		return archivalStrategyRepository.findAll();
	}

	public ArchivalDeletionStrategy createArchivalDeletionStrategy(ArchivalDeletionStrategy archivalStrategy) {
		sendMessageToArchiver(archivalStrategy);
		return archivalDeletionStrategyRepository.save(archivalStrategy);
	}

	public List<ArchivalDeletionStrategy> getAllArchivalDeletionStrategies() {
		return archivalDeletionStrategyRepository.findAll();
	}

	private void sendMessageToArchiver(ArchivalDeletionStrategy archivalStrategy) {
		String apiUrl = "http://localhost:8081/deletion-strategies";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String requestBody = objectMapper.writeValueAsString(archivalStrategy);
			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
			System.out.println("Sending message to archiver service to add a deletion strategy " + archivalStrategy);
			ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity,
					String.class);

			int statusCode = responseEntity.getStatusCodeValue();
			System.out.println("sendMessageToArchiver Status Code: " + statusCode);
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

}
