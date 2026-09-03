package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.SparePartType;
import com.crm.crm_backend.repository.SparePartTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SparePartTypeServiceImpl {

    private final SparePartTypeRepository repository;

    public SparePartType save(SparePartType request) {

        SparePartType sparePartType;

        if (request.getId() != null) {

            sparePartType = repository.findById(request.getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Spare Part Type not found."));

            sparePartType.setUpdatedAt(LocalDateTime.now());

        } else {

            if (repository.existsBySparePartTypeIgnoreCase(
                    request.getSparePartType())) {

                throw new RuntimeException(
                        "Spare Part Type already exists.");
            }

            if (repository.existsBySparePartTypeCodeIgnoreCase(
                    request.getSparePartTypeCode())) {

                throw new RuntimeException(
                        "Spare Part Type Code already exists.");
            }

            sparePartType = new SparePartType();

            sparePartType.setCreatedAt(LocalDateTime.now());
        }

        sparePartType.setSparePartType(
                request.getSparePartType());

        sparePartType.setSparePartTypeCode(
                request.getSparePartTypeCode());

       

        sparePartType.setActive(
                request.getActive());

        return repository.save(sparePartType);
    }

    public List<SparePartType> getAll() {

        return repository.findAll();
    }

    public SparePartType getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Spare Part Type not found."));
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Spare Part Type not found.");
        }

        repository.deleteById(id);
    }

    public SparePartType changeStatus(
            Long id,
            Boolean active) {

        SparePartType sparePartType =
                repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Spare Part Type not found."));

        sparePartType.setActive(active);

        sparePartType.setUpdatedAt(
                LocalDateTime.now());

        return repository.save(sparePartType);
    }
}