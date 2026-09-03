package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ServicePriority;
import com.crm.crm_backend.repository.ServicePriorityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicePriorityServiceImpl {

    private final ServicePriorityRepository repository;

    public ServicePriority save(ServicePriority request){

        ServicePriority priority;

        if(request.getId()!=null){

            priority = repository.findById(request.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Service Priority not found."));

            priority.setUpdatedAt(LocalDateTime.now());

        }else{

            if(repository.existsByServicePriorityIgnoreCase(request.getServicePriority())){
                throw new RuntimeException("Service Priority already exists.");
            }

            priority = new ServicePriority();
            priority.setCreatedAt(LocalDateTime.now());

        }

        priority.setServicePriority(request.getServicePriority());
        priority.setDescription(request.getDescription());
        priority.setActive(request.getActive());

        return repository.save(priority);

    }

    public List<ServicePriority> getAll(){

        return repository.findAll();

    }

    public ServicePriority getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Service Priority not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public ServicePriority changeStatus(Long id, Boolean active){

        ServicePriority priority = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Service Priority not found."));

        priority.setActive(active);
        priority.setUpdatedAt(LocalDateTime.now());

        return repository.save(priority);

    }

}