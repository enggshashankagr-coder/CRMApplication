package com.crm.crm_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crm.crm_backend.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

	@Query(value = "select * from mst_country where id = ?1 and deleted = false", nativeQuery = true)
	public Optional<Country> getbyId(Long id);

}
