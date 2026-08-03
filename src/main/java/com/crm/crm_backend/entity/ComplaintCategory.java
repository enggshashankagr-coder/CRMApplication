package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_complaint_category")
public class ComplaintCategory extends AuditableEntity {



    @Column(nullable = false, unique = true)
    private String category;

    @Column(length = 500)
    private String categoryDescription;


}