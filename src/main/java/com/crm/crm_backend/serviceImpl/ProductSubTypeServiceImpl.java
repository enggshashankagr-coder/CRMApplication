package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ProductSubType;
import com.crm.crm_backend.repository.ProductSubTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductSubTypeServiceImpl {

    private final ProductSubTypeRepository repository;

    public ProductSubType save(ProductSubType productSubType){

        if(productSubType.getId()!=null){

            ProductSubType dbProductSubType =
                    repository.findById(productSubType.getId())
                    .orElseThrow(() ->
                    new RuntimeException("Product Sub Type not found."));

            dbProductSubType.setProductSubType(productSubType.getProductSubType());
            dbProductSubType.setProductSubTypeCode(productSubType.getProductSubTypeCode());
            dbProductSubType.setProductType(productSubType.getProductType());
            dbProductSubType.setActive(productSubType.getActive());

            dbProductSubType.setUpdatedBy(productSubType.getUpdatedBy());
            dbProductSubType.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbProductSubType);

        }else{

            if(repository.existsByProductSubTypeIgnoreCase(productSubType.getProductSubType())){
                throw new RuntimeException("Product Sub Type already exists.");
            }

            if(repository.existsByProductSubTypeCodeIgnoreCase(productSubType.getProductSubTypeCode())){
                throw new RuntimeException("Product Sub Type Code already exists.");
            }

            productSubType.setCreatedAt(LocalDateTime.now());

            return repository.save(productSubType);

        }

    }

    public List<ProductSubType> getAll(){

        return repository.findAll();

    }

    public ProductSubType getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Product Sub Type not found."));

    }

    public List<ProductSubType> getByProductType(Long productTypeId){

        return repository.findByProductTypeId(productTypeId);

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public ProductSubType changeStatus(Long id,Boolean active){

        ProductSubType productSubType =
                repository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Product Sub Type not found."));

        productSubType.setActive(active);
        productSubType.setUpdatedAt(LocalDateTime.now());

        return repository.save(productSubType);

    }

}