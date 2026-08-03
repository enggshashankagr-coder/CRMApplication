package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_complaint_charge_type")
public class ComplaintChargeType extends AuditableEntity {

    @Column(name = "charge_type", nullable = false, unique = true)
    private String chargeType;

    @Column(name = "charge_description", length = 500)
    private String chargeDescription;



}