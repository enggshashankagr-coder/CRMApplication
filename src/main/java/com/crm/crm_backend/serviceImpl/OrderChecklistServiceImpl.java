package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.OrderChecklist;
import com.crm.crm_backend.repository.OrderChecklistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderChecklistServiceImpl {

    private final OrderChecklistRepository repository;

    public OrderChecklist save(OrderChecklist checklist){

        if(checklist.getId()!=null){

            OrderChecklist dbChecklist = repository.findById(checklist.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Order Checklist not found."));

            dbChecklist.setChecklistName(checklist.getChecklistName());
            dbChecklist.setSequenceNo(checklist.getSequenceNo());
            dbChecklist.setChecklistFor(checklist.getChecklistFor());
            dbChecklist.setDescription(checklist.getDescription());
            dbChecklist.setActive(checklist.getActive());

            dbChecklist.setUpdatedBy(checklist.getUpdatedBy());
            dbChecklist.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbChecklist);

        }else{

            if(repository.existsByChecklistNameIgnoreCase(checklist.getChecklistName())){
                throw new RuntimeException("Checklist already exists.");
            }

            checklist.setCreatedAt(LocalDateTime.now());

            return repository.save(checklist);

        }

    }

    public List<OrderChecklist> getAll(){

        return repository.findAll();

    }

    public OrderChecklist getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order Checklist not found."));

    }

    public List<OrderChecklist> getByChecklistFor(String checklistFor){

        return repository.findByChecklistForIgnoreCase(checklistFor);

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public OrderChecklist changeStatus(Long id, Boolean active){

        OrderChecklist checklist = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order Checklist not found."));

        checklist.setActive(active);
        checklist.setUpdatedAt(LocalDateTime.now());

        return repository.save(checklist);

    }

}