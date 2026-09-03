package com.crm.crm_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_spare_part")
public class SparePart extends AuditableEntity{


    @Column(name = "spare_part_name", nullable = false, unique = true)
    private String sparePartName;

    @Column(name = "spare_part_code", nullable = false, unique = true)
    private String sparePartCode;

    @Column(length = 500)
    private String description;

   
}