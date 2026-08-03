package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_order_checklist")
public class OrderChecklist extends AuditableEntity {


    @Column(name = "checklist_name", nullable = false, unique = true)
    private String checklistName;

    @Column(name = "sequence_no", nullable = false)
    private Integer sequenceNo;

    @Column(name = "checklist_for", nullable = false)
    private String checklistFor;

    @Column(length = 500)
    private String description;



}