package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.SparePart;

public interface SparePartRepository
        extends JpaRepository<SparePart, Long> {

    boolean existsBySparePartNameIgnoreCase(String sparePartName);

    boolean existsBySparePartCodeIgnoreCase(String sparePartCode);
}