package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_complaint_source")
public class ComplaintSource extends AuditableEntity{


    @Column(name = "complaint_source", nullable = false, unique = true)
    private String complaintSource;

    @Column(length = 500)
    private String description;



}