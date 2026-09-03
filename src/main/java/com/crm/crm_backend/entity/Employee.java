package com.crm.crm_backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_employee")
public class Employee extends AuditableEntity {

  

    // =====================================================
    // EMPLOYEE DETAILS
    // =====================================================

    @Column(name = "employee_name", length = 200)
    private String employeeName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "mobile_number", nullable = false, length = 20)
    private String mobileNumber;

    @Column(name = "alternate_phone", length = 20)
    private String alternatePhone;

    // =====================================================
    // ORGANIZATION DETAILS
    // =====================================================

    @Column(name = "designation_id")
    private Long designationId;

    @Column(name = "manager_l1_id")
    private Long managerL1Id;

    @Column(name = "manager_l2_id")
    private Long managerL2Id;

    @Column(name = "manager_l3_id")
    private Long managerL3Id;

    @Column(nullable = false, length = 20)
    private String status = "ACTIVE";

    // =====================================================
    // JOINING / LEAVING
    // =====================================================

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @Column(name = "date_of_leaving")
    private LocalDate dateOfLeaving;

    // =====================================================
    // ACCOUNT DETAILS
    // =====================================================

    @Column(name = "smtp_password", nullable = false)
    private String smtpPassword;

    @Column(name = "functional_role", length = 100)
    private String functionalRole;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Column(name = "region_id", nullable = false)
    private Long regionId;

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    // =====================================================
    // USER IMAGE
    // =====================================================

    @Column(name = "user_image")
    private String userImage;

    @Lob
    @Column(name = "user_image_data")
    private byte[] userImageData;

    @Column(name = "user_image_content_type")
    private String userImageContentType;

    // =====================================================
    // SIGNATURE
    // =====================================================

    @Lob
    @Column(name = "signature")
    private String signature;

    // =====================================================
    // AUDIT
    // =====================================================

   
}