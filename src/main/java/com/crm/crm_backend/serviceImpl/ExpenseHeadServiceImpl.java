package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ExpenseHead;
import com.crm.crm_backend.repository.ExpenseHeadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseHeadServiceImpl {

    private final ExpenseHeadRepository repository;

    public ExpenseHead save(ExpenseHead expenseHead){

        if(expenseHead.getId()!=null){

            ExpenseHead dbExpense = repository.findById(expenseHead.getId())
                    .orElseThrow(()->
                            new RuntimeException("Expense Head not found."));

            dbExpense.setExpenseHead(expenseHead.getExpenseHead());
            dbExpense.setExpenseType(expenseHead.getExpenseType());
            dbExpense.setExpenseNature(expenseHead.getExpenseNature());
            dbExpense.setLimitRs(expenseHead.getLimitRs());
            dbExpense.setUnit(expenseHead.getUnit());
            dbExpense.setShortName(expenseHead.getShortName());
            dbExpense.setActive(expenseHead.getActive());

            dbExpense.setUpdatedBy(expenseHead.getUpdatedBy());
            dbExpense.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbExpense);

        }else{

            if(repository.existsByExpenseHeadIgnoreCase(expenseHead.getExpenseHead())){
                throw new RuntimeException("Expense Head already exists.");
            }

            expenseHead.setCreatedAt(LocalDateTime.now());

            return repository.save(expenseHead);

        }

    }

    public List<ExpenseHead> getAll(){

        return repository.findAll();

    }

    public ExpenseHead getById(Long id){

        return repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Expense Head not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public ExpenseHead changeStatus(Long id,Boolean active){

        ExpenseHead expenseHead = repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Expense Head not found."));

        expenseHead.setActive(active);
        expenseHead.setUpdatedAt(LocalDateTime.now());

        return repository.save(expenseHead);

    }

}