package com.archival.archivalservice.dto;

import java.util.List;

import com.archival.archivalservice.entity.Student;

public class StudentDto {

	List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
