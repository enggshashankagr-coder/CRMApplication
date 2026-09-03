package com.crm.crm_backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_quotation_charge_head")
public class QuotationChargeHead extends AuditableEntity {


    @Column(name="quotation_charge_name",nullable=false,unique=true)
    private String quotationChargeName;

    @Column(name="add_less",nullable=false)
    private String addLess;

    @Column(name="value_type",nullable=false)
    private String valueType;

    @Column(name="sequence_no")
    private Integer sequenceNo;

    @Column(name="default_value",precision=12,scale=2)
    private BigDecimal defaultValue;

    @Column(name="calculate_running_total")
    private Boolean calculateRunningTotal=true;

    @Column(name="is_gst")
    private Boolean isGst=false;


}