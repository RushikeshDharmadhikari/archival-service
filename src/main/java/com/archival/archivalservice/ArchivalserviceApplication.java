package com.archival.archivalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class ArchivalserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchivalserviceApplication.class, args);
	}

}
