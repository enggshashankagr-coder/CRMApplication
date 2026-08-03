package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.PinCode;
import com.crm.crm_backend.repository.PinCodeRepository;

@Service
public class PinCodeServiceImpl {
	  
	private  PinCodeRepository pincodeRepository;

	    public PinCode save(PinCode pincode) {

	        if (pincode.getId() != null) {

	        	PinCode dbPincode = pincodeRepository.findById(pincode.getId())
	                    .orElseThrow(() -> new RuntimeException("Pincode not found."));

	            dbPincode.setPincode(pincode.getPincode());
	            dbPincode.setArea(pincode.getArea());
	            dbPincode.setActive(pincode.getActive());
	            dbPincode.setUpdatedBy(pincode.getUpdatedBy());
	            dbPincode.setUpdatedAt(LocalDateTime.now());

	            return pincodeRepository.save(dbPincode);

	        } else {

	            if (pincodeRepository.existsByPincode(pincode.getPincode())) {
	                throw new RuntimeException("Pincode already exists.");
	            }

	            pincode.setCreatedAt(LocalDateTime.now());

	            return pincodeRepository.save(pincode);
	        }

	    }

	    public List<PinCode> getAll() {
	        return pincodeRepository.findAll();
	    }

	    public PinCode getById(Long id) {

	        return pincodeRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Pincode not found."));
	    }

	    public List<PinCode> getByArea(Long areaId) {
	        return pincodeRepository.findByAreaId(areaId);
	    }

	    public void delete(Long id) {

	        PinCode pincode = getById(id);

	        pincodeRepository.delete(pincode);

	    }
}
