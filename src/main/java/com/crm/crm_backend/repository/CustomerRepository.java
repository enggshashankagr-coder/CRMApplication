package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.Customer;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    boolean existsByEnquiryNoIgnoreCase(
            String enquiryNo);

    boolean existsByCustomerNameIgnoreCase(
            String customerName);

    boolean existsByCustomerCodeIgnoreCase(
            String customerCode);
}