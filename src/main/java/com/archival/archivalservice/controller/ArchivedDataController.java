package com.archival.archivalservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archival.archivalservice.entity.AdminRecords;
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

	/**
	 * TODO: Archiving of other types of data is not yet supported. Adding this
	 * method to demonstrate RBAC constraints for URLs.
	 */
	@GetMapping("/admin-archives")
	List<AdminRecords> fetchAllArchivedAdmins() {
		return new ArrayList<AdminRecords>();
	}

}
