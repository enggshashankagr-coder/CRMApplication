package com.crm.crm_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.LibraryFileSubCategory;

public interface LibraryFileSubCategoryRepository
        extends JpaRepository<LibraryFileSubCategory,Long>{

    boolean existsByFileSubCategoryIgnoreCase(String fileSubCategory);

    List<LibraryFileSubCategory> findByFileCategoryId(Long fileCategoryId);

}