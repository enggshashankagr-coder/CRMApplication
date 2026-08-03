package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ComplaintMode;
import com.crm.crm_backend.repository.ComplaintModeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplaintModeServiceImpl {

    private final ComplaintModeRepository repository;

    public ComplaintMode save(ComplaintMode complaintMode) {

        if (complaintMode.getId() != null) {

            ComplaintMode dbMode = repository.findById(complaintMode.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Complaint Mode not found."));

            dbMode.setComplaintMode(complaintMode.getComplaintMode());
            dbMode.setDescription(complaintMode.getDescription());
            dbMode.setActive(complaintMode.getActive());
            dbMode.setUpdatedBy(complaintMode.getUpdatedBy());
            dbMode.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbMode);

        } else {

            if (repository.existsByComplaintModeIgnoreCase(
                    complaintMode.getComplaintMode())) {

                throw new RuntimeException(
                        "Complaint Mode already exists.");
            }

            complaintMode.setCreatedAt(LocalDateTime.now());

            return repository.save(complaintMode);
        }

    }

    public List<ComplaintMode> getAll() {

        return repository.findAll();

    }

    public ComplaintMode getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint Mode not found."));

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public ComplaintMode changeStatus(Long id, Boolean active) {

        ComplaintMode complaintMode = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint Mode not found."));

        complaintMode.setActive(active);
        complaintMode.setUpdatedAt(LocalDateTime.now());

        return repository.save(complaintMode);

    }

}