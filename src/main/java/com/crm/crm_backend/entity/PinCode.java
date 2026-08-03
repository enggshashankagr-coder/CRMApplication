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
@Table(name = "mst_pincode")
public class PinCode extends AuditableEntity 
{
	  @Column(nullable = false, unique = true, length = 10)
	    private String pincode;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "area_id")
	    private Area area;

		
}
