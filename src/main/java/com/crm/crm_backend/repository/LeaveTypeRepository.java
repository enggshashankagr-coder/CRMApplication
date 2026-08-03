package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

    boolean existsByLeaveTypeIgnoreCase(String leaveType);

}