package com.ffuentese.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ffuentese.model.Device;

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

	public interface DeviceRepository extends CrudRepository<Device, Long> {
	
		//@Query(value = "select d.name, d.brand, d.model from employee e join device d on e.id=d.employee_id where e.id = ?1", nativeQuery = true)
		public List<Device> findAllByEmployeeId(Long employee_id);
		
		public List<Device> findAllByBrandId(Long brand_id);
		
		public List<Device> findAllByModelId(Long device_id);
		
		@Query(value = "select d.id, d.type_id, d.brand_id, d.model_id, d.employee_id, d.serial, d.notes from device d inner join employee e on e.id=d.employee_id inner join department de on e.dept_id=de.id inner join brand b on d.brand_id=b.id  where de.id = ?1", nativeQuery = true)
		public List<Device> findDevicesByDepartmentId(Long department_id);
		
		@Query(value = "select d.id, d.type_id, d.brand_id, d.model_id, d.employee_id, d.serial, d.notes from device d join employee e on d.employee_id=e.id where e.branch_id=?1", nativeQuery = true)
		public List<Device> findDevicesByBranchId(Long branch_id);
	}

