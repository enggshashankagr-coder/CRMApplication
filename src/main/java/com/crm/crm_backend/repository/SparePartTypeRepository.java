package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.SparePartType;

public interface SparePartTypeRepository
        extends JpaRepository<SparePartType, Long> {

    boolean existsBySparePartTypeIgnoreCase(String sparePartType);

    boolean existsBySparePartTypeCodeIgnoreCase(String sparePartTypeCode);
}