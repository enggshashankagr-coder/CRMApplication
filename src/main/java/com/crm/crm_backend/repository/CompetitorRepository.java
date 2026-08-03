package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.Competitor;

public interface CompetitorRepository extends JpaRepository<Competitor, Long>{
	boolean existsByCompetitorNameIgnoreCase(String competitorName);
	
}
