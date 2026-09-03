package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="mst_quotation_tc_parameter_detail")
public class QuotationTCParameterDetail extends AuditableEntity{



    @Column(name="parameter_name",nullable=false)
    private String parameterName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parameter_head_id",nullable=false)
    private QuotationTCParameterHead parameterHead;

    @Column(name="default_value")
    private String defaultValue;

    @Column(length=500)
    private String description;


}
