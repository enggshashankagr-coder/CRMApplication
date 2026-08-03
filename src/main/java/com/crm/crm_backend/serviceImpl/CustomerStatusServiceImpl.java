package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.CustomerStatus;
import com.crm.crm_backend.repository.CustomerStatusRepository;

import lombok.RequiredArgsConstructor;

@Service
	@RequiredArgsConstructor
	public class CustomerStatusServiceImpl {

	    private final CustomerStatusRepository repository;

	    public CustomerStatus save(CustomerStatus customerStatus){

	        if(customerStatus.getId()!=null){

	            CustomerStatus dbStatus = repository.findById(customerStatus.getId())
	                    .orElseThrow(() ->
	                            new RuntimeException("Customer Status not found."));

	            dbStatus.setName(customerStatus.getName());
	            dbStatus.setShortName(customerStatus.getShortName());
	            dbStatus.setStatus(customerStatus.getStatus());
	            dbStatus.setDescription(customerStatus.getDescription());
	            dbStatus.setLevel(customerStatus.getLevel());
	            dbStatus.setProbability(customerStatus.getProbability());
	            dbStatus.setActive(customerStatus.getActive());
	            dbStatus.setUpdatedBy(customerStatus.getUpdatedBy());
	            dbStatus.setUpdatedAt(LocalDateTime.now());

	            return repository.save(dbStatus);

	        }else{

	            if(repository.existsByNameIgnoreCase(customerStatus.getName())){
	                throw new RuntimeException("Customer Status already exists.");
	            }

	            customerStatus.setCreatedAt(LocalDateTime.now());

	            return repository.save(customerStatus);
	        }

	    }

	    public List<CustomerStatus> getAll(){

	        return repository.findAll();

	    }

	    public CustomerStatus getById(Long id){

	        return repository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Customer Status not found."));

	    }

	    public void delete(Long id){

	        repository.deleteById(id);

	    }

	    public CustomerStatus changeStatus(Long id, Boolean active){

	        CustomerStatus customerStatus = repository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Customer Status not found."));

	        customerStatus.setActive(active);
	        customerStatus.setUpdatedAt(LocalDateTime.now());

	        return repository.save(customerStatus);

	    }

	}