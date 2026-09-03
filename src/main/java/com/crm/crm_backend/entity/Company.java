package com.crm.crm_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_company")
public class Company extends AuditableEntity{

	
	    @Column(name = "company_name", nullable = false, unique = true)
	    private String companyName;

	    @Column(length = 20)
	    private String mobile;

	    @Column(length = 20)
	    private String phone;

	    @Column(length = 500)
	    private String address;

	    @Column(length = 50)
	    private String gstin;

	    @Column(name = "pan_no", length = 20)
	    private String panNo;

	    @Column(length = 150)
	    private String email;

	    @Column(length = 150)
	    private String email1;

	    @Column(name = "country_id")
	    private Long countryId;

	    @Column(name = "state_id")
	    private Long stateId;

	    @Column(name = "city_id")
	    private Long cityId;

	    @Column(name = "pin_code_id")
	    private Long pinCodeId;

	    @Column(nullable = false)
	    private Boolean active = true;

	    /*
	     * Physical file name/path
	     */
	    @Column(name = "company_image")
	    private String companyImage;

	    /*
	     * Actual image stored in database
	     */
	    @Lob
	    @Column(name = "company_image_data")
	    private byte[] companyImageData;

	    /*
	     * Example:
	     * image/png
	     * image/jpeg
	     */
	    @Column(name = "company_image_content_type")
	    private String companyImageContentType;

	
	}

    
