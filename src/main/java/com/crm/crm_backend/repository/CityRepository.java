package com.crm.crm_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

	 // Optional<City>getById(Long id);

	    List<City> findByStateId(Long stateId);
}
