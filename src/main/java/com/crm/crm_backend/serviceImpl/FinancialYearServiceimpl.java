package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.FinancialYear;
import com.crm.crm_backend.repository.FinancialYearRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FinancialYearServiceimpl {

    private final FinancialYearRepository repository;

    public FinancialYear save(FinancialYear financialYear){

        if(financialYear.getId()!=null){

            FinancialYear dbYear = repository.findById(financialYear.getId())
                    .orElseThrow(()->
                            new RuntimeException("Financial Year not found."));

            dbYear.setFinancialYear(financialYear.getFinancialYear());
            dbYear.setShortName(financialYear.getShortName());
            dbYear.setStartDate(financialYear.getStartDate());
            dbYear.setEndDate(financialYear.getEndDate());
            dbYear.setActive(financialYear.getActive());

            dbYear.setUpdatedBy(financialYear.getUpdatedBy());
            dbYear.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbYear);

        }else{

            if(repository.existsByFinancialYearIgnoreCase(
                    financialYear.getFinancialYear())){

                throw new RuntimeException("Financial Year already exists.");

            }

            financialYear.setCreatedAt(LocalDateTime.now());

            return repository.save(financialYear);

        }

    }

    public List<FinancialYear> getAll(){

        return repository.findAll();

    }

    public FinancialYear getById(Long id){

        return repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Financial Year not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public FinancialYear changeStatus(Long id,Boolean active){

        FinancialYear financialYear = repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Financial Year not found."));

        financialYear.setActive(active);
        financialYear.setUpdatedAt(LocalDateTime.now());

        return repository.save(financialYear);

    }

}