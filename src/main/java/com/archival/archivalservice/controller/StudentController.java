package com.archival.archivalservice.controller;

import java.util.List;

import javax.persistence.EntityListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.archival.archivalservice.entity.Student;
import com.archival.archivalservice.repository.StudentRepository;

@RestController
@EntityListeners(AuditingEntityListener.class)
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@PostMapping("/students")
	Student addStudent(@RequestBody Student newEmployee) {
		return studentRepository.save(newEmployee);
	}

	@GetMapping("/students")
	List<Student> all() {
		return studentRepository.findAll();
	}

}
