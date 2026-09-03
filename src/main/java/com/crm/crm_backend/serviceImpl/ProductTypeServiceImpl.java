package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ProductType;
import com.crm.crm_backend.repository.ProductTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl {

    private final ProductTypeRepository repository;

    public ProductType save(ProductType request){

        ProductType productType;

        if(request.getId()!=null){

            productType = repository.findById(request.getId())
                    .orElseThrow(()->
                            new RuntimeException("Product Type not found."));

            productType.setUpdatedAt(LocalDateTime.now());

        }else{

            if(repository.existsByProductTypeIgnoreCase(request.getProductType())){
                throw new RuntimeException("Product Type already exists.");
            }

            if(repository.existsByProductTypeCodeIgnoreCase(request.getProductTypeCode())){
                throw new RuntimeException("Product Type Code already exists.");
            }

            productType = new ProductType();
            productType.setCreatedAt(LocalDateTime.now());

        }

        productType.setProductType(request.getProductType());
        productType.setProductTypeCode(request.getProductTypeCode());
        productType.setActive(request.getActive());

        return repository.save(productType);

    }

    public List<ProductType> getAll(){

        return repository.findAll();

    }

    public ProductType getById(Long id){

        return repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Product Type not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public ProductType changeStatus(Long id,Boolean active){

        ProductType productType = repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Product Type not found."));

        productType.setActive(active);
        productType.setUpdatedAt(LocalDateTime.now());

        return repository.save(productType);

    }

}