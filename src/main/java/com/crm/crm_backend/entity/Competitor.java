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
@Table(name = "mst_competitor")
public class Competitor extends AuditableEntity {
	
	
	 	@Column(nullable = false)
	    private String competitorName;

	    @Column(length = 500)
	    private String address;

	    private BigDecimal price;

	    private BigDecimal amc;

}
