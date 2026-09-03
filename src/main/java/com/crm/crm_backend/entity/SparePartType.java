package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_spare_part_type")
public class SparePartType extends AuditableEntity {


    @Column(name = "spare_part_type", nullable = false, unique = true)
    private String sparePartType;

    @Column(name = "spare_part_type_code", nullable = false, unique = true)
    private String sparePartTypeCode;

  
}