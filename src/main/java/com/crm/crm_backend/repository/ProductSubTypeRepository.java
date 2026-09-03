package com.crm.crm_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ProductSubType;

public interface ProductSubTypeRepository
        extends JpaRepository<ProductSubType,Long>{

    boolean existsByProductSubTypeIgnoreCase(String productSubType);

    boolean existsByProductSubTypeCodeIgnoreCase(String productSubTypeCode);

    List<ProductSubType> findByProductTypeId(Long productTypeId);

}