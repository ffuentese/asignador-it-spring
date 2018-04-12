package com.ffuentese.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ffuentese.model.Model;

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

	public interface ModelRepository extends CrudRepository<Model, Long> {
		
		@Query(value = "SELECT distinct m.id, m.name, m.brand_id, m.type_id, m.qty from model m inner join type t on m.type_id=t.id inner join brand b on m.brand_id=b.id where t.id=?1 and b.id=?2 and m.qty > 0", nativeQuery = true)
		public List<Model> findAllByBrandId(Long type_id, Long brand_id);
		
		
	}

