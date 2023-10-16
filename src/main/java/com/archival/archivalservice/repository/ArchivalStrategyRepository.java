package com.archival.archivalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archival.archivalservice.entity.ArchivalStrategy;

public interface ArchivalStrategyRepository extends JpaRepository<ArchivalStrategy, Long> {

}
