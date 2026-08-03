package com.crm.crm_backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_financial_year")
public class FinancialYear extends AuditableEntity {

 

    @Column(name="financial_year",nullable=false,unique=true)
    private String financialYear;

    @Column(name="short_name")
    private String shortName;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

}