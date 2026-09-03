package com.crm.crm_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.OrderLostReason;

public interface OrderLostReasonRepository
        extends JpaRepository<OrderLostReason, Long> {

    boolean existsByOrderLostReasonIgnoreCase(String orderLostReason);

}