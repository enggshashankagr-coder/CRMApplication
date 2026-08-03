package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.FinancialYear;

public interface FinancialYearRepository
        extends JpaRepository<FinancialYear, Long>{

    boolean existsByFinancialYearIgnoreCase(String financialYear);

}