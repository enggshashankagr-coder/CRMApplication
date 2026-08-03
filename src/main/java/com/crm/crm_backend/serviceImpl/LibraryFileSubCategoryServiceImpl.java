package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.LibraryFileSubCategory;
import com.crm.crm_backend.repository.LibraryFileSubCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryFileSubCategoryServiceImpl {

    private final LibraryFileSubCategoryRepository repository;

    public LibraryFileSubCategory save(
            LibraryFileSubCategory subCategory){

        if(subCategory.getId()!=null){

            LibraryFileSubCategory dbSubCategory =
                    repository.findById(subCategory.getId())
                    .orElseThrow(()->
                    new RuntimeException(
                    "Library File Sub Category not found."));

            dbSubCategory.setFileSubCategory(
                    subCategory.getFileSubCategory());

            dbSubCategory.setShortName(
                    subCategory.getShortName());

            dbSubCategory.setDescription(
                    subCategory.getDescription());

            dbSubCategory.setFileCategory(
                    subCategory.getFileCategory());

            dbSubCategory.setActive(
                    subCategory.getActive());

            dbSubCategory.setUpdatedBy(
                    subCategory.getUpdatedBy());

            dbSubCategory.setUpdatedAt(
                    LocalDateTime.now());

            return repository.save(dbSubCategory);

        }else{

            if(repository.existsByFileSubCategoryIgnoreCase(
                    subCategory.getFileSubCategory())){

                throw new RuntimeException(
                        "File Sub Category already exists.");

            }

            subCategory.setCreatedAt(LocalDateTime.now());

            return repository.save(subCategory);

        }

    }

    public List<LibraryFileSubCategory> getAll(){

        return repository.findAll();

    }

    public LibraryFileSubCategory getById(Long id){

        return repository.findById(id)
                .orElseThrow(()->
                new RuntimeException(
                "Library File Sub Category not found."));

    }

    public List<LibraryFileSubCategory> getByCategory(
            Long categoryId){

        return repository.findByFileCategoryId(categoryId);

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public LibraryFileSubCategory changeStatus(
            Long id,
            Boolean active){

        LibraryFileSubCategory subCategory =
                repository.findById(id)
                .orElseThrow(()->
                new RuntimeException(
                "Library File Sub Category not found."));

        subCategory.setActive(active);
        subCategory.setUpdatedAt(LocalDateTime.now());

        return repository.save(subCategory);

    }

}