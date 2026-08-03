package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.PaymentPlan;

public interface PaymentPlanRepository
        extends JpaRepository<PaymentPlan, Long> {

    boolean existsByPaymentPlanIgnoreCase(String paymentPlan);

    boolean existsByShortNameIgnoreCase(String shortName);

}