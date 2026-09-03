package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ServicePriority;

public interface ServicePriorityRepository
        extends JpaRepository<ServicePriority, Long> {

    boolean existsByServicePriorityIgnoreCase(String servicePriority);

}
