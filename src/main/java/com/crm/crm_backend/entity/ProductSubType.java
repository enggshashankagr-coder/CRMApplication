package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_product_sub_type")
public class ProductSubType extends  AuditableEntity{

    @Column(name="product_sub_type",nullable=false)
    private String productSubType;

    @Column(name="product_sub_type_code",nullable=false)
    private String productSubTypeCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_type_code",nullable=false)
    private ProductType productType;

 

}