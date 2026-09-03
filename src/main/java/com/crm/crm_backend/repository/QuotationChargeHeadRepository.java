package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.QuotationChargeHead;

public interface QuotationChargeHeadRepository
extends JpaRepository<QuotationChargeHead,Long>{

boolean existsByQuotationChargeNameIgnoreCase(
    String quotationChargeName);

}
