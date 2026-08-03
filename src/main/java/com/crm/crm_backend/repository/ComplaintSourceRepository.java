package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ComplaintSource;

public interface ComplaintSourceRepository
        extends JpaRepository<ComplaintSource, Long> {

    boolean existsByComplaintSourceIgnoreCase(String complaintSource);

}