package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ProductCategory;
import com.crm.crm_backend.repository.ProductCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl {

    private final ProductCategoryRepository repository;

    public ProductCategory save(ProductCategory category) {

        if (category.getId() != null) {

            ProductCategory dbCategory = repository.findById(category.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Product Category not found."));

            dbCategory.setProductCategory(category.getProductCategory());
            dbCategory.setProductCategoryCode(category.getProductCategoryCode());
            //dbCategory.setDescription(category.getDescription());
            dbCategory.setActive(category.getActive());

            dbCategory.setUpdatedBy(category.getUpdatedBy());
            dbCategory.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbCategory);

        } else {

            if (repository.existsByProductCategoryIgnoreCase(category.getProductCategory())) {
                throw new RuntimeException("Product Category already exists.");
            }

            if (repository.existsByProductCategoryCodeIgnoreCase(category.getProductCategoryCode())) {
                throw new RuntimeException("Product Category Code already exists.");
            }

            category.setCreatedAt(LocalDateTime.now());

            return repository.save(category);

        }

    }

    public List<ProductCategory> getAll() {

        return repository.findAll();

    }

    public ProductCategory getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Category not found."));

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public ProductCategory changeStatus(Long id, Boolean active) {

        ProductCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Category not found."));

        category.setActive(active);
        category.setUpdatedAt(LocalDateTime.now());

        return repository.save(category);

    }

}