package com.crm.crm_backend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_order_lost_reason")
public class OrderLostReason extends AuditableEntity {


    @Column(name = "order_lost_reason", nullable = false, unique = true)
    private String orderLostReason;

   

}