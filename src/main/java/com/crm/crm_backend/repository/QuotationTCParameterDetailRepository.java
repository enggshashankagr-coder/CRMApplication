package com.crm.crm_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.QuotationTCParameterDetail;

public interface QuotationTCParameterDetailRepository
extends JpaRepository<QuotationTCParameterDetail,Long>{

boolean existsByParameterNameIgnoreCase(String parameterName);

List<QuotationTCParameterDetail> findByParameterHeadId(Long parameterHeadId);

}
