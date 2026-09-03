package com.crm.crm_backend.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {

    private Long id;

    private String companyName;

    private String mobile;

    private String phone;

    private String address;

    private String gstin;

    private String panNo;

    private String email;

    private String email1;

    private Long countryId;

    private Long stateId;

    private Long cityId;

    private Long pinCodeId;

    private Boolean active;

    private String createdBy;

    private MultipartFile file;
}