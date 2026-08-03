package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.State;
import com.crm.crm_backend.repository.StateRepository;

@Service
public class StateServiceImpl {
		
		@Autowired
	 private StateRepository stateRepository;

	    public State save(State state) {

	        if (state.getId() != null) {

	            State existingState = stateRepository.findById(state.getId())
	                    .orElseThrow(() -> new RuntimeException("State not found"));

	            existingState.setStateCode(state.getStateCode());
	            existingState.setStateName(state.getStateName());
	            existingState.setCountry(state.getCountry());
	            existingState.setActive(state.getActive());
	            existingState.setUpdatedBy(state.getUpdatedBy());
	            existingState.setUpdatedAt(LocalDateTime.now());

	            return stateRepository.save(existingState);

	        } else {

	            if (stateRepository.existsByStateCode(state.getStateCode())) {
	                throw new RuntimeException("State Code already exists.");
	            }

	            if (stateRepository.existsByStateName(state.getStateName())) {
	                throw new RuntimeException("State Name already exists.");
	            }

	            state.setCreatedAt(LocalDateTime.now());

	            return stateRepository.save(state);
	        }
	    }

	    public List<State> getAll() {
	        return stateRepository.findAll();
	    }

	    public State getById(Long id) {

	        return stateRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("State not found."));
	    }

	    public List<State> getByCountry(Long countryId) {
	        return stateRepository.findByCountryId(countryId);
	    }

	    public void delete(Long id) {

	        State state = getById(id);

	        stateRepository.delete(state);
	    }

	

}
