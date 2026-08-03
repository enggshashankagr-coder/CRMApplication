package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_payment_plan")
public class PaymentPlan extends AuditableEntity{

    @Column(name = "payment_plan", nullable = false, unique = true)
    private String paymentPlan;

    @Column(name = "short_name", nullable = false, length = 20)
    private String shortName;

    @Column(length = 500)
    private String description;


}