package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.Destination;
import com.crm.crm_backend.repository.DestinationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl{

    private final DestinationRepository repository;

    public Destination save(Destination destination) {

        if (destination.getId() != null) {

            Destination dbDestination = repository.findById(destination.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Destination not found."));

            dbDestination.setDestination(destination.getDestination());
            dbDestination.setShortName(destination.getShortName());
            dbDestination.setDescription(destination.getDescription());
            dbDestination.setActive(destination.getActive());
            dbDestination.setUpdatedBy(destination.getUpdatedBy());
            dbDestination.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbDestination);

        } else {

            if (repository.existsByDestinationIgnoreCase(destination.getDestination())) {
                throw new RuntimeException("Destination already exists.");
            }

            destination.setCreatedAt(LocalDateTime.now());

            return repository.save(destination);

        }

    }

    public List<Destination> getAll() {

        return repository.findAll();

    }

    public Destination getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Destination not found."));

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public Destination changeStatus(Long id, Boolean active) {

        Destination destination = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Destination not found."));

        destination.setActive(active);
        destination.setUpdatedAt(LocalDateTime.now());

        return repository.save(destination);

    }

}