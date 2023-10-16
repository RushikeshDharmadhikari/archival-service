package com.archival.archivalservice.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.archival.archivalservice.dto.StudentDto;
import com.archival.archivalservice.entity.ArchivalStrategy;
import com.archival.archivalservice.entity.Student;
import com.archival.archivalservice.entity.TimeUnit;
import com.archival.archivalservice.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataArchivingService {

	private static final String STUDENT = "student";

	@Autowired
	ArchivalStrategyService archivalStrategyService;

	@Autowired
	StudentRepository studentRepository;

	/**
	 * TODO: Use a factory pattern here
	 */
	public void archiveData() {
		System.out.println();
		System.out.println("Archiving the data by reading the archival strategies.");
		System.out.println();
		
		List<ArchivalStrategy> archivalStrategies = archivalStrategyService.getAllArchivalStrategies();
		System.out.println(String.format("ArchivalStratgies : %s", archivalStrategies));
		System.out.println();
		System.out.println();
		
		for (ArchivalStrategy archivalStrategy : archivalStrategies) {
			if (archivalStrategy.getTableName().equalsIgnoreCase(STUDENT)) {
				handleStudentArchival(archivalStrategy);
			}
		}
		return;

	}

	private void handleStudentArchival(ArchivalStrategy archivalStrategy) {
		Calendar calendar = Calendar.getInstance(); // Get a calendar instance
		calendar.setTime(new Date()); // Set it to the current date

		if (archivalStrategy.getArchiveUnit().equals(TimeUnit.MONTHS)) {
			calendar.add(Calendar.MONTH, -1 * archivalStrategy.getArchiveValue());
		} else if (archivalStrategy.getArchiveUnit().equals(TimeUnit.YEARS)) {
			calendar.add(Calendar.YEAR, -1 * archivalStrategy.getArchiveValue());
		}

		Date expectedDate = calendar.getTime();
		List<Student> students = studentRepository.findByCreatedAtBefore(expectedDate);
	
		sendToArchiver(students);
		
	}

	private void sendToArchiver(List<Student> students) {
		if (students == null || students.isEmpty()) {
			return;
		}
		String apiUrl = "http://localhost:8081/student-archives";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		ObjectMapper objectMapper = new ObjectMapper();
		StudentDto studentDto = new StudentDto();
		studentDto.setStudents(students);

		try {
			String requestBody = objectMapper.writeValueAsString(studentDto);
			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity,
					String.class);

			// Get the response status code
			int statusCode = responseEntity.getStatusCodeValue();
			System.out.println("sendToArchiver Status Code: " + statusCode);
			System.out.println();
			System.out.println();
			
			if (statusCode == 200) {
				System.out.println("Deleting the records from primary DB which have been sent to archiver");
				System.out.println();
				studentRepository.deleteAll(students);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
