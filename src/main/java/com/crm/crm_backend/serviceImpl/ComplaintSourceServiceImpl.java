package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ComplaintSource;
import com.crm.crm_backend.repository.ComplaintSourceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplaintSourceServiceImpl {

    private final ComplaintSourceRepository repository;

    public ComplaintSource save(ComplaintSource complaintSource) {

        if (complaintSource.getId() != null) {

            ComplaintSource dbSource = repository.findById(complaintSource.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Complaint Source not found."));

            dbSource.setComplaintSource(complaintSource.getComplaintSource());
            dbSource.setDescription(complaintSource.getDescription());
            dbSource.setActive(complaintSource.getActive());
            dbSource.setUpdatedBy(complaintSource.getUpdatedBy());
            dbSource.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbSource);

        } else {

            if (repository.existsByComplaintSourceIgnoreCase(
                    complaintSource.getComplaintSource())) {

                throw new RuntimeException(
                        "Complaint Source already exists.");
            }

            complaintSource.setCreatedAt(LocalDateTime.now());

            return repository.save(complaintSource);
        }

    }

    public List<ComplaintSource> getAll() {

        return repository.findAll();

    }

    public ComplaintSource getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint Source not found."));

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public ComplaintSource changeStatus(Long id, Boolean active) {

        ComplaintSource complaintSource = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint Source not found."));

        complaintSource.setActive(active);
        complaintSource.setUpdatedAt(LocalDateTime.now());

        return repository.save(complaintSource);

    }

}