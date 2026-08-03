package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.PaymentMode;

public interface PaymentModeRepository
        extends JpaRepository<PaymentMode, Long> {

    boolean existsByPaymentModeIgnoreCase(String paymentMode);

    boolean existsByPaymentModeCodeIgnoreCase(String paymentModeCode);

}