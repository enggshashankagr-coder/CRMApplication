package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ComplaintChargeType;
import com.crm.crm_backend.repository.ComplaintChargeTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplaintChargeTypeServiceImpl {

    private final ComplaintChargeTypeRepository repository;

    public ComplaintChargeType save(ComplaintChargeType chargeType) {

        if (chargeType.getId() != null) {

            ComplaintChargeType dbChargeType = repository.findById(chargeType.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Complaint Charge Type not found."));

            dbChargeType.setChargeType(chargeType.getChargeType());
            dbChargeType.setChargeDescription(chargeType.getChargeDescription());
            dbChargeType.setActive(chargeType.getActive());
            dbChargeType.setUpdatedBy(chargeType.getUpdatedBy());
            dbChargeType.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbChargeType);

        } else {

            if (repository.existsByChargeTypeIgnoreCase(chargeType.getChargeType())) {
                throw new RuntimeException("Complaint Charge Type already exists.");
            }

            chargeType.setCreatedAt(LocalDateTime.now());

            return repository.save(chargeType);
        }

    }

    public List<ComplaintChargeType> getAll() {

        return repository.findAll();

    }

    public ComplaintChargeType getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint Charge Type not found."));

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public ComplaintChargeType changeStatus(Long id, Boolean active) {

        ComplaintChargeType chargeType = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint Charge Type not found."));

        chargeType.setActive(active);
        chargeType.setUpdatedAt(LocalDateTime.now());

        return repository.save(chargeType);

    }
}
