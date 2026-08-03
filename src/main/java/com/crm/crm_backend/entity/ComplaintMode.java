package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_complaint_mode")
public class ComplaintMode extends AuditableEntity {



    @Column(name = "complaint_mode", nullable = false, unique = true)
    private String complaintMode;

    @Column(length = 500)
    private String description;

}