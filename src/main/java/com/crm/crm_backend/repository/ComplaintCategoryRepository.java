package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ComplaintCategory;

	public interface ComplaintCategoryRepository extends JpaRepository<ComplaintCategory, Long>{

	    boolean existsByCategoryIgnoreCase(String category);
}
