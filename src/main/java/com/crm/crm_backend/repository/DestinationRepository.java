package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.Destination;

public interface DestinationRepository
        extends JpaRepository<Destination, Long> {

    boolean existsByDestinationIgnoreCase(String destination);

}