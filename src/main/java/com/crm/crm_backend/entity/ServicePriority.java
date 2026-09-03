package com.crm.crm_backend.entity;



import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_service_priority")
public class ServicePriority extends AuditableEntity{



    @Column(name = "service_priority", nullable = false, unique = true)
    private String servicePriority;

    @Column(length = 500)
    private String description;


}