package com.archival.archivalservice.dummydata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.archival.archivalservice.dto.Role;
import com.archival.archivalservice.entity.Student;
import com.archival.archivalservice.entity.User;
import com.archival.archivalservice.repository.StudentRepository;
import com.archival.archivalservice.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println("Ingesting the dummy data into database");
		System.out.println();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		calendar.add(Calendar.YEAR, -10);

		Date tenYearsAgo = calendar.getTime();

		List<Student> studentList = new ArrayList<Student>();

		studentList.add(new Student("Tom1", tenYearsAgo));
		studentList.add(new Student("Tom2", tenYearsAgo));
		studentList.add(new Student("Tom3", tenYearsAgo));
		studentList.add(new Student("Tom4", tenYearsAgo));
		studentList.add(new Student("Tom5", tenYearsAgo));
		studentList.add(new Student("Tom6", tenYearsAgo));
		studentList.add(new Student("Tom7", tenYearsAgo));
		studentList.add(new Student("Tom8", tenYearsAgo));
		studentList.add(new Student("Tom9", tenYearsAgo));
		studentList.add(new Student("Tom10", tenYearsAgo));

		Calendar calendar2 = Calendar.getInstance(); // Get a calendar instance
		calendar2.setTime(new Date()); // Set it to the current date

		// Subtract 10 years
		calendar2.add(Calendar.MONTH, -6);

		Date sixMonthAgo = calendar2.getTime();

		studentList.add(new Student("Hari1", sixMonthAgo));
		studentList.add(new Student("Hari2", sixMonthAgo));
		studentList.add(new Student("Hari3", sixMonthAgo));
		studentList.add(new Student("Hari4", sixMonthAgo));
		studentList.add(new Student("Hari5", sixMonthAgo));
		studentList.add(new Student("Hari6", sixMonthAgo));
		studentList.add(new Student("Hari7", sixMonthAgo));
		studentList.add(new Student("Hari8", sixMonthAgo));
		studentList.add(new Student("Hari9", sixMonthAgo));
		studentList.add(new Student("Hari10", sixMonthAgo));

		studentList.add(new Student("Rushikesh1", new Date()));
		studentList.add(new Student("Rushikesh2", new Date()));
		studentList.add(new Student("Rushikesh3", new Date()));
		studentList.add(new Student("Rushikesh4", new Date()));
		studentList.add(new Student("Rushikesh5", new Date()));
		studentList.add(new Student("Rushikesh6", new Date()));
		studentList.add(new Student("Rushikesh7", new Date()));
		studentList.add(new Student("Rushikesh8", new Date()));
		studentList.add(new Student("Rushikesh9", new Date()));
		studentList.add(new Student("Rushikesh10", new Date()));

		studentRepository.saveAll(studentList);

		User adminUser = new User();
		adminUser.setUsername("admin");
		adminUser.setPassword("admin");
		String encodedPassword = passwordEncoder.encode(adminUser.getPassword());
		adminUser.setPassword(encodedPassword);
		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ADMIN);
		adminUser.setRoles(roles);
		userRepository.save(adminUser);
	
		User student = new User();
		student.setUsername("student");
		student.setPassword("student");
		String studentPassword = passwordEncoder.encode(student.getPassword());
		student.setPassword(studentPassword);
		Set<Role> roles1 = new HashSet<Role>();
		roles1.add(Role.STUDENT);
		student.setRoles(roles1);
		userRepository.save(student);
		
		
	}
}
