package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ComplaintMode;

public interface ComplaintModeRepository
        extends JpaRepository<ComplaintMode, Long> {

    boolean existsByComplaintModeIgnoreCase(String complaintMode);

}