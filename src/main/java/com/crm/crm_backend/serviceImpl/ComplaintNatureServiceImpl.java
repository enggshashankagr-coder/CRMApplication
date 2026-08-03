package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ComplaintNature;
import com.crm.crm_backend.repository.ComplaintNatureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplaintNatureServiceImpl {

    private final ComplaintNatureRepository repository;

    public ComplaintNature save(ComplaintNature complaintNature) {

        if (complaintNature.getId() != null) {

            ComplaintNature dbNature = repository.findById(complaintNature.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Complaint Nature not found."));

            dbNature.setComplaintNature(complaintNature.getComplaintNature());
            dbNature.setDescription(complaintNature.getDescription());
            dbNature.setActive(complaintNature.getActive());
            dbNature.setUpdatedBy(complaintNature.getUpdatedBy());
            dbNature.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbNature);

        } else {

            if (repository.existsByComplaintNatureIgnoreCase(
                    complaintNature.getComplaintNature())) {

                throw new RuntimeException(
                        "Complaint Nature already exists.");
            }

            complaintNature.setCreatedAt(LocalDateTime.now());

            return repository.save(complaintNature);
        }

    }

    public List<ComplaintNature> getAll() {

        return repository.findAll();

    }

    public ComplaintNature getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint Nature not found."));

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public ComplaintNature changeStatus(Long id, Boolean active) {

        ComplaintNature complaintNature = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint Nature not found."));

        complaintNature.setActive(active);
        complaintNature.setUpdatedAt(LocalDateTime.now());

        return repository.save(complaintNature);

    }

}