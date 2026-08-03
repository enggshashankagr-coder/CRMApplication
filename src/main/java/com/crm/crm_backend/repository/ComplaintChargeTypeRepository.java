package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ComplaintChargeType;

public interface ComplaintChargeTypeRepository
extends JpaRepository<ComplaintChargeType, Long> {

boolean existsByChargeTypeIgnoreCase(String chargeType);

}