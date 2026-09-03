package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_product_category")
public class ProductCategory extends AuditableEntity{



    @Column(name = "product_category", nullable = false, unique = true)
    private String productCategory;

    @Column(name = "product_category_code", nullable = false, unique = true)
    private String productCategoryCode;


}