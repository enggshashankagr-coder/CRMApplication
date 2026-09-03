package com.crm.crm_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    boolean existsByEmailIgnoreCase(String email);

    Optional<Employee> findByEmailIgnoreCase(String email);
}