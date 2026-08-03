package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_payment_mode")
public class PaymentMode extends AuditableEntity {


    @Column(name = "payment_mode", nullable = false, unique = true)
    private String paymentMode;

    @Column(name = "payment_mode_code", nullable = false, unique = true)
    private String paymentModeCode;

    @Column(length = 500)
    private String description;


}