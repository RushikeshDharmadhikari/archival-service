package com.archival.archivalservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.archival.archivalservice.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}