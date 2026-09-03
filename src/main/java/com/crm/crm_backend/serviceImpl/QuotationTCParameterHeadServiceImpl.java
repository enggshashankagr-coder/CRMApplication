package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.QuotationTCParameterHead;
import com.crm.crm_backend.repository.QuotationTCParameterHeadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuotationTCParameterHeadServiceImpl {

    private final QuotationTCParameterHeadRepository repository;

    public QuotationTCParameterHead save(
            QuotationTCParameterHead request){

        QuotationTCParameterHead parameter;

        if(request.getId()!=null){

            parameter = repository.findById(request.getId())
                    .orElseThrow(()->
                            new RuntimeException("Parameter not found."));

            parameter.setUpdatedAt(LocalDateTime.now());

        }else{

            if(repository.existsByParameterHeadIgnoreCase(request.getParameterHead())){
                throw new RuntimeException("Parameter already exists.");
            }

            parameter = new QuotationTCParameterHead();
            parameter.setCreatedAt(LocalDateTime.now());

        }

        parameter.setParameterHead(request.getParameterHead());
        parameter.setSequenceNo(request.getSequenceNo());
        parameter.setActive(request.getActive());

        return repository.save(parameter);

    }

    public List<QuotationTCParameterHead> getAll(){

        return repository.findAll();

    }

    public QuotationTCParameterHead getById(Long id){

        return repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Parameter not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public QuotationTCParameterHead changeStatus(
            Long id,
            Boolean active){

        QuotationTCParameterHead parameter =
                repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Parameter not found."));

        parameter.setActive(active);
        parameter.setUpdatedAt(LocalDateTime.now());

        return repository.save(parameter);

    }

}
