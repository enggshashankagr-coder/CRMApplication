package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_preventive_maintenance")
public class PreventiveMaintenance extends AuditableEntity {

 
    @Column(name = "preventive_maintenance", nullable = false, unique = true)
    private String preventiveMaintenance;

    @Column(name = "preventive_maintenance_code", nullable = false, unique = true)
    private String preventiveMaintenanceCode;



}