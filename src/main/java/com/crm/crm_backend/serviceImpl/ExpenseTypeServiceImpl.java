package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ExpenseType;
import com.crm.crm_backend.repository.ExpenseTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseTypeServiceImpl {

    private final ExpenseTypeRepository repository;

    public ExpenseType save(ExpenseType expenseType){

        if(expenseType.getId()!=null){

            ExpenseType dbExpenseType = repository.findById(expenseType.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Expense Type not found."));

            dbExpenseType.setExpenseType(expenseType.getExpenseType());
            dbExpenseType.setAmount(expenseType.getAmount());
            dbExpenseType.setShortName(expenseType.getShortName());
            dbExpenseType.setActive(expenseType.getActive());

            dbExpenseType.setUpdatedBy(expenseType.getUpdatedBy());
            dbExpenseType.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbExpenseType);

        }else{

            if(repository.existsByExpenseTypeIgnoreCase(expenseType.getExpenseType())){
                throw new RuntimeException("Expense Type already exists.");
            }

            expenseType.setCreatedAt(LocalDateTime.now());

            return repository.save(expenseType);

        }

    }

    public List<ExpenseType> getAll(){

        return repository.findAll();

    }

    public ExpenseType getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Expense Type not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public ExpenseType changeStatus(Long id, Boolean active){

        ExpenseType expenseType = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Expense Type not found."));

        expenseType.setActive(active);
        expenseType.setUpdatedAt(LocalDateTime.now());

        return repository.save(expenseType);

    }

}