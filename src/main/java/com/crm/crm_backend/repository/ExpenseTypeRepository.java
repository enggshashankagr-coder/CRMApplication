package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ExpenseType;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long>{

    boolean existsByExpenseTypeIgnoreCase(String expenseType);

}