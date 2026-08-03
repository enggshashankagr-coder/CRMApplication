package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ComplaintNature;

public interface ComplaintNatureRepository
        extends JpaRepository<ComplaintNature, Long> {

    boolean existsByComplaintNatureIgnoreCase(String complaintNature);

}