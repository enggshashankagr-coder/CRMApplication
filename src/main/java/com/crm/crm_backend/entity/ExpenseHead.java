package com.crm.crm_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_expense_head")
public class ExpenseHead extends AuditableEntity {


    @Column(name="expense_head",nullable=false,unique=true)
    private String expenseHead;

    @Column(name="expense_type")
    private String expenseType;

    @Column(name="expense_nature")
    private String expenseNature;

    @Column(name="limit_rs",precision=12,scale=2)
    private BigDecimal limitRs;

    private String unit;

    @Column(name="short_name")
    private String shortName;


}