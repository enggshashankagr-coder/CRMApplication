package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.CustomerStatus;

public interface CustomerStatusRepository
        extends JpaRepository<CustomerStatus, Long>{

    boolean existsByNameIgnoreCase(String name);

}