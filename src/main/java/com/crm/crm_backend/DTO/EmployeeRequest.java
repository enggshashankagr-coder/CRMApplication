package com.crm.crm_backend.DTO;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {

    private Long id;

    private String employeeName;

    private String email;

    private String mobileNumber;

    private String alternatePhone;

    private Long designationId;

    private Long managerL1Id;

    private Long managerL2Id;

    private Long managerL3Id;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoining;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfLeaving;

    private String smtpPassword;

    private String functionalRole;

    private Long companyId;

    private Long departmentId;

    private Long regionId;

    private Long branchId;

    private String signature;

    private String createdBy;

    private MultipartFile file;
}