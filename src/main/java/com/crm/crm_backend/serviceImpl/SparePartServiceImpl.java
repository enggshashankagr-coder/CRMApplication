package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.SparePart;
import com.crm.crm_backend.repository.SparePartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SparePartServiceImpl {

    private final SparePartRepository repository;

    public SparePart save(SparePart request) {

        SparePart sparePart;

        if (request.getId() != null) {

            sparePart = repository.findById(request.getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Spare Part not found."));

            sparePart.setUpdatedAt(LocalDateTime.now());

        } else {

            if (repository.existsBySparePartNameIgnoreCase(
                    request.getSparePartName())) {

                throw new RuntimeException(
                        "Spare Part Name already exists.");
            }

            if (repository.existsBySparePartCodeIgnoreCase(
                    request.getSparePartCode())) {

                throw new RuntimeException(
                        "Spare Part Code already exists.");
            }

            sparePart = new SparePart();

            sparePart.setCreatedAt(LocalDateTime.now());
        }

        sparePart.setSparePartName(
                request.getSparePartName());

        sparePart.setSparePartCode(
                request.getSparePartCode());

        sparePart.setDescription(
                request.getDescription());

        sparePart.setActive(
                request.getActive());

        return repository.save(sparePart);
    }

    public List<SparePart> getAll() {

        return repository.findAll();
    }

    public SparePart getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Spare Part not found."));
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Spare Part not found.");
        }

        repository.deleteById(id);
    }

    public SparePart changeStatus(
            Long id,
            Boolean active) {

        SparePart sparePart = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Spare Part not found."));

        sparePart.setActive(active);
        sparePart.setUpdatedAt(
                LocalDateTime.now());

        return repository.save(sparePart);
    }
}