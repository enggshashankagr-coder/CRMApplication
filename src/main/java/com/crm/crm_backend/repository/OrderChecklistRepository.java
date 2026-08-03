package com.crm.crm_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.OrderChecklist;

public interface OrderChecklistRepository extends JpaRepository<OrderChecklist, Long>{

    boolean existsByChecklistNameIgnoreCase(String checklistName);

    List<OrderChecklist> findByChecklistForIgnoreCase(String checklistFor);

}