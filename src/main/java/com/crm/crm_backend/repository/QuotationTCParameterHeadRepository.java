package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.QuotationTCParameterHead;

public interface QuotationTCParameterHeadRepository
extends JpaRepository<QuotationTCParameterHead, Long> {

boolean existsByParameterHeadIgnoreCase(String parameterHead);

}
