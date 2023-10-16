package com.archival.archivalservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archival.archivalservice.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findByCreatedAtBefore(Date cutoffDate);
}
