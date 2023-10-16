package com.archival.archivalservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.archival.archivalservice.entity.ArchivalDeletionStrategy;
import com.archival.archivalservice.entity.ArchivalStrategy;
import com.archival.archivalservice.service.ArchivalStrategyService;

@RestController
public class ArchivalStrategyController {

	@Autowired
	ArchivalStrategyService archivalStrategyService;

	@PostMapping(path = "/archival-strategies")
	ArchivalStrategy createArchivalStrategy(@RequestBody ArchivalStrategy archivalStrategy) {
		return archivalStrategyService.createArchivalStrategy(archivalStrategy);
	}

	@GetMapping(path = "/archival-strategies")
	List<ArchivalStrategy> getAllArchivalStrategies() {
		return archivalStrategyService.getAllArchivalStrategies();
	}

	@PostMapping(path = "/deletion-strategies")
	ArchivalDeletionStrategy createArchivalDeletionStrategy(@RequestBody ArchivalDeletionStrategy archivalStrategy) {
		return archivalStrategyService.createArchivalDeletionStrategy(archivalStrategy);
	}

	@GetMapping(path = "/deletion-strategies")
	List<ArchivalDeletionStrategy> getAllArchivalDeletionStrategies() {
		return archivalStrategyService.getAllArchivalDeletionStrategies();
	}

}
