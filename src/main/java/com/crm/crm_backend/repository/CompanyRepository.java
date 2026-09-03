package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.Company;

public interface CompanyRepository
        extends JpaRepository<Company, Long> {

    boolean existsByCompanyNameIgnoreCase(
            String companyName);

}