package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.PreventiveMaintenance;

public interface PreventiveMaintenanceRepository
        extends JpaRepository<PreventiveMaintenance, Long> {

    boolean existsByPreventiveMaintenanceIgnoreCase(
            String preventiveMaintenance);

    boolean existsByPreventiveMaintenanceCodeIgnoreCase(
            String preventiveMaintenanceCode);

}