package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_complaint_nature")
public class ComplaintNature extends AuditableEntity {

    @Column(name = "complaint_nature", nullable = false, unique = true)
    private String complaintNature;

    @Column(length = 500)
    private String description;


}