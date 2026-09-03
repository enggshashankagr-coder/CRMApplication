package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.QuotationChargeHead;
import com.crm.crm_backend.repository.QuotationChargeHeadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuotationChargeHeadServiceimpl {

	    private final QuotationChargeHeadRepository repository;

	    public QuotationChargeHead save(
	            QuotationChargeHead request){

	        QuotationChargeHead charge;

	        if(request.getId()!=null){

	            charge = repository.findById(request.getId())
	                    .orElseThrow(()->
	                    new RuntimeException("Quotation Charge not found"));

	            charge.setUpdatedAt(LocalDateTime.now());

	        }else{

	            if(repository.existsByQuotationChargeNameIgnoreCase(
	                    request.getQuotationChargeName())){

	                throw new RuntimeException(
	                        "Quotation Charge already exists.");

	            }

	            charge = new QuotationChargeHead();
	            charge.setCreatedAt(LocalDateTime.now());

	        }

	        charge.setQuotationChargeName(request.getQuotationChargeName());
	        charge.setAddLess(request.getAddLess());
	        charge.setValueType(request.getValueType());
	        charge.setSequenceNo(request.getSequenceNo());
	        charge.setDefaultValue(request.getDefaultValue());
	        charge.setCalculateRunningTotal(request.getCalculateRunningTotal());
	        charge.setIsGst(request.getIsGst());
	        charge.setActive(request.getActive());
	        
	        return repository.save(charge);

	    }

	    public List<QuotationChargeHead> getAll(){

	        return repository.findAll();

	    }

	    public QuotationChargeHead getById(Long id){

	        return repository.findById(id)
	                .orElseThrow(()->
	                new RuntimeException("Quotation Charge not found"));

	    }

	    public void delete(Long id){

	        repository.deleteById(id);

	    }

	    public QuotationChargeHead changeStatus(
	            Long id,
	            Boolean active){

	        QuotationChargeHead charge =
	                repository.findById(id)
	                .orElseThrow(()->
	                new RuntimeException("Quotation Charge not found"));

	        charge.setActive(active);
	        charge.setUpdatedAt(LocalDateTime.now());

	        return repository.save(charge);

	    }

	}
