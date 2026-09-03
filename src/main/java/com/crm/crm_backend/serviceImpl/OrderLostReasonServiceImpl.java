package com.crm.crm_backend.serviceImpl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.OrderLostReason;
import com.crm.crm_backend.repository.OrderLostReasonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLostReasonServiceImpl {

    private final OrderLostReasonRepository repository;

    public OrderLostReason save(OrderLostReason request){

        OrderLostReason reason;

        if(request.getId()!=null){

            reason = repository.findById(request.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Order Lost Reason not found."));

            reason.setUpdatedAt(LocalDateTime.now());

        }else{

            if(repository.existsByOrderLostReasonIgnoreCase(request.getOrderLostReason())){
                throw new RuntimeException("Order Lost Reason already exists.");
            }

            reason = new OrderLostReason();
            reason.setCreatedAt(LocalDateTime.now());

        }

        reason.setOrderLostReason(request.getOrderLostReason());
        reason.setActive(request.getActive());

        return repository.save(reason);

    }

    public List<OrderLostReason> getAll(){

        return repository.findAll();

    }

    public OrderLostReason getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order Lost Reason not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public OrderLostReason changeStatus(Long id, Boolean active){

        OrderLostReason reason = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order Lost Reason not found."));

        reason.setActive(active);
        reason.setUpdatedAt(LocalDateTime.now());

        return repository.save(reason);

    }

}
