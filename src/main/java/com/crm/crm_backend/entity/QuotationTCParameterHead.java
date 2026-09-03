package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_quotation_tc_parameter_head")
public class QuotationTCParameterHead extends AuditableEntity{

  

    @Column(name = "parameter_head", nullable = false, unique = true)
    private String parameterHead;

    @Column(name = "sequence_no")
    private Integer sequenceNo;

  

}