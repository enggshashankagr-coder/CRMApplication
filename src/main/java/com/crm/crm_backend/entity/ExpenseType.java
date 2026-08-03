package com.crm.crm_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_expense_type")
public class ExpenseType extends AuditableEntity {

    @Column(name = "expense_type", nullable = false, unique = true)
    private String expenseType;

    @Column(precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "short_name", length = 20)
    private String shortName;


}