package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ProductCategory;

public interface ProductCategoryRepository
        extends JpaRepository<ProductCategory, Long> {

    boolean existsByProductCategoryIgnoreCase(String productCategory);

    boolean existsByProductCategoryCodeIgnoreCase(String productCategoryCode);

}