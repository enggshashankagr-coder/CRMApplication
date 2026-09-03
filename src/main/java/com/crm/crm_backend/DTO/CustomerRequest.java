package com.crm.crm_backend.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private Long id;

    // =====================================================
    // ENQUIRY
    // =====================================================

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate enquiryDate;

    private String enquiryNo;

    // =====================================================
    // CUSTOMER
    // =====================================================

    private String customerName;

    private String shortName;

    private String customerCode;

    private String address;

    private String contactPerson;

    private String mobile;

    private String telephone;

    private String webAddress;

    private String email;

    private String currency;

    // =====================================================
    // LOCATION
    // =====================================================

    private Long countryId;

    private Long stateId;

    private Long cityId;

    private Long subLocationId;

    private Long pinCodeId;

    // =====================================================
    // MANAGEMENT
    // =====================================================

    private Long acManagerId;

    private Long initiatedById;

    private Long industryId;

    private Long segmentId;

    private Long sourceId;

    private Long referenceId;

    private Long statusId;

    private Long categoryId;

    // =====================================================
    // ORDER
    // =====================================================

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderExpectedBy;

    private String remarks;

    private Boolean active;

    private String createdBy;

    private String updatedBy;
}