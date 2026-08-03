package com.crm.crm_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findByCityId(Long cityId);

    boolean existsByAreaCode(String areaCode);

    boolean existsByAreaName(String areaName);

}
