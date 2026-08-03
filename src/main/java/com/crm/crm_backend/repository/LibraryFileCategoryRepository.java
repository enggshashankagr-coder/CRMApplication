package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.LibraryFileCategory;

public interface LibraryFileCategoryRepository extends JpaRepository<LibraryFileCategory, Long> {

    boolean existsByFileCategoryIgnoreCase(String fileCategory);

}