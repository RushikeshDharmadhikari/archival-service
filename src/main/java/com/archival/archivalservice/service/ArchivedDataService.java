package com.archival.archivalservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.archival.archivalservice.entity.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ArchivedDataService {

	public List<Student> getDataFromArchiver() {
		String apiUrl = "http://localhost:8081/student-archives";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			System.out.println("Sending message to archiver service to get all archived student records");
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

			int statusCode = responseEntity.getStatusCodeValue();

			List<Student> studentList = objectMapper.readValue(responseEntity.getBody(),
					new TypeReference<List<Student>>() {
					});

			System.out.println("sendMessageToArchiver Status Code: " + statusCode);
			
			return studentList;
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return new ArrayList();
	}
	
}
