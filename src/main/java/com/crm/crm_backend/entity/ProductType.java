package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="mst_product_type")
public class ProductType extends AuditableEntity {

  

    @Column(name="product_type",nullable=false,unique=true)
    private String productType;

    @Column(name="product_type_code",nullable=false,unique=true)
    private String productTypeCode;

   

}