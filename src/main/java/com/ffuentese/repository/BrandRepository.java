package com.ffuentese.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ffuentese.model.Brand;

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

	public interface BrandRepository extends CrudRepository<Brand, Long> {
		@Query(value = "SELECT distinct b.id, b.name from model m inner join type t on m.type_id=t.id inner join brand b on m.brand_id=b.id where t.id=?1 and m.qty > 0", nativeQuery = true)
		public List<Brand> findAllByTypeId(Long type_id);
	}

