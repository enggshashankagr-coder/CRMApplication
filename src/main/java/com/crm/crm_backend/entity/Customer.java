package com.crm.crm_backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_customer")
public class Customer extends AuditableEntity {

   

    // =====================================================
    // ENQUIRY DETAILS
    // =====================================================

    @Column(name = "enquiry_date", nullable = false)
    private LocalDate enquiryDate;

    @Column(name = "enquiry_no", nullable = false, unique = true)
    private String enquiryNo;

    // =====================================================
    // CUSTOMER DETAILS
    // =====================================================

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "customer_code")
    private String customerCode;

    @Column(nullable = false)
    private String address;

    @Column(name = "contact_person", nullable = false)
    private String contactPerson;

    @Column(nullable = false)
    private String mobile;

    private String telephone;

    @Column(name = "web_address", nullable = false)
    private String webAddress;

    private String email;

    private String currency;

    // =====================================================
    // LOCATION
    // =====================================================

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "state_id", nullable = false)
    private Long stateId;

    @Column(name = "city_id", nullable = false)
    private Long cityId;

    @Column(name = "sub_location_id")
    private Long subLocationId;

    @Column(name = "pin_code_id")
    private Long pinCodeId;

    // =====================================================
    // CUSTOMER MANAGEMENT
    // =====================================================

    @Column(name = "ac_manager_id")
    private Long acManagerId;

    @Column(name = "initiated_by_id")
    private Long initiatedById;

    @Column(name = "industry_id")
    private Long industryId;

    @Column(name = "segment_id")
    private Long segmentId;

    @Column(name = "source_id")
    private Long sourceId;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "category_id")
    private Long categoryId;

    // =====================================================
    // ORDER
    // =====================================================

    @Column(name = "order_expected_by")
    private LocalDate orderExpectedBy;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    // =====================================================
    // STATUS
    // =====================================================

   
}