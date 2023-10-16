package com.archival.archivalservice.controller;

import java.util.List;

import javax.persistence.EntityListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archival.archivalservice.entity.Student;
import com.archival.archivalservice.service.ArchivedDataService;

@RestController
@EntityListeners(AuditingEntityListener.class)
public class ArchivedDataController {
	
	@Autowired
	ArchivedDataService archivedDataService;

	@GetMapping("/student-archives")
	List<Student> fetchAllArchivedStudents() {
		return archivedDataService.getDataFromArchiver();
	}

}
