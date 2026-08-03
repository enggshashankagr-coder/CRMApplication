package com.crm.crm_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_area")
public class Area extends AuditableEntity {

	
	  @Column(nullable = false)
	    private String areaCode;

	    @Column(nullable = false)
	    private String areaName;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "city_id")
	    private City city;

}
