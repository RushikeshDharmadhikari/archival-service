package com.archival.archivalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archival.archivalservice.entity.ArchivalDeletionStrategy;

public interface ArchivalDeletionStrategyRepository extends JpaRepository<ArchivalDeletionStrategy, Long> {

}
