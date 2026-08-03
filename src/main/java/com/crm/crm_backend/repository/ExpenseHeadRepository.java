package com.crm.crm_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.ExpenseHead;

public interface ExpenseHeadRepository extends JpaRepository<ExpenseHead,Long>{

    boolean existsByExpenseHeadIgnoreCase(String expenseHead);

}