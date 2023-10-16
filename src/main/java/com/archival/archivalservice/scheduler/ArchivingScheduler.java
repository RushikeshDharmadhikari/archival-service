package com.archival.archivalservice.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.archival.archivalservice.service.DataArchivingService;

@Service
public class ArchivingScheduler {

	@Autowired
	private DataArchivingService dataArchivingService;

	@Scheduled(cron = "*/55 * * * * *")
	public void archiveData() {
		System.out.println("Calling the archival service for data archival.");
		dataArchivingService.archiveData();
	}
	
}
