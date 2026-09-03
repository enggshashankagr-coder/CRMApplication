package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.DTO.QuotationTCParameterDetailRequest;
import com.crm.crm_backend.entity.QuotationTCParameterDetail;
import com.crm.crm_backend.entity.QuotationTCParameterHead;
import com.crm.crm_backend.repository.QuotationTCParameterDetailRepository;
import com.crm.crm_backend.repository.QuotationTCParameterHeadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuotationTCParameterDetailServiceImpl {

    private final QuotationTCParameterDetailRepository repository;

    private final QuotationTCParameterHeadRepository parameterHeadRepository;

    public QuotationTCParameterDetail save(
            QuotationTCParameterDetailRequest request){

        QuotationTCParameterHead parameterHead =
                parameterHeadRepository.findById(request.getParameterHeadId())
                .orElseThrow(() ->
                new RuntimeException("Parameter Head not found."));

        QuotationTCParameterDetail detail;

        if(request.getId()!=null){

            detail = repository.findById(request.getId())
                    .orElseThrow(() ->
                    new RuntimeException("Parameter Detail not found."));

            detail.setUpdatedAt(LocalDateTime.now());

        }else{

            if(repository.existsByParameterNameIgnoreCase(request.getParameterName())){

                throw new RuntimeException("Parameter Name already exists.");

            }

            detail = new QuotationTCParameterDetail();
            detail.setCreatedAt(LocalDateTime.now());

        }

        detail.setParameterName(request.getParameterName());
        detail.setParameterHead(parameterHead);
        detail.setDefaultValue(request.getDefaultValue());
        detail.setDescription(request.getDescription());
        detail.setActive(request.getActive());

        return repository.save(detail);

    }

    public List<QuotationTCParameterDetail> getAll(){

        return repository.findAll();

    }

    public QuotationTCParameterDetail getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Parameter Detail not found."));

    }

    public List<QuotationTCParameterDetail> getByParameterHead(Long parameterHeadId){

        return repository.findByParameterHeadId(parameterHeadId);

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public QuotationTCParameterDetail changeStatus(
            Long id,
            Boolean active){

        QuotationTCParameterDetail detail =
                repository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Parameter Detail not found."));

        detail.setActive(active);
        detail.setUpdatedAt(LocalDateTime.now());

        return repository.save(detail);

    }

}
