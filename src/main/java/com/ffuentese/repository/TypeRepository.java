package com.ffuentese.repository;

import org.springframework.data.repository.CrudRepository;

import com.ffuentese.model.Type;

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

	public interface TypeRepository extends CrudRepository<Type, Long> {

	}

