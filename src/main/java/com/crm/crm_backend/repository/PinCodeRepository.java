package com.crm.crm_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.PinCode;

public interface PinCodeRepository extends JpaRepository<PinCode, Long> {

    List<PinCode> findByAreaId(Long areaId);

    boolean existsByPincode(String pincode); 

}
