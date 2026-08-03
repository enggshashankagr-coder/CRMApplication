package com.crm.crm_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_leave_type")
public class LeaveType extends AuditableEntity{



    @Column(name = "leave_type", nullable = false, unique = true)
    private String leaveType;

    @Column(name = "short_name", length = 20)
    private String shortName;

    @Column(length = 500)
    private String description;

  

}