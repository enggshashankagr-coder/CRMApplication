package com.crm.crm_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.State;

public interface StateRepository extends JpaRepository<State, Long>{
	
	List<State> findByCountryId(Long countryId);

	    boolean existsByStateCode(String stateCode);

	    boolean existsByStateName(String stateName);
}
