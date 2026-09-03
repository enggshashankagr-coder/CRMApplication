package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ProductType;

public interface ProductTypeRepository
        extends JpaRepository<ProductType,Long>{

    boolean existsByProductTypeIgnoreCase(String productType);

    boolean existsByProductTypeCodeIgnoreCase(String productTypeCode);

}