package com.crm.crm_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_customer_status")
public class CustomerStatus extends AuditableEntity {


    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "short_name", length = 20)
    private String shortName;

    @Column(length = 30)
    private String status;

    @Column(length = 500)
    private String description;

    @Column(name = "level_no")
    private Integer level;

    @Column(precision = 5, scale = 2)
    private BigDecimal probability;


}