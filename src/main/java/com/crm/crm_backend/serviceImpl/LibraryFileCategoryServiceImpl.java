package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.LibraryFileCategory;
import com.crm.crm_backend.repository.LibraryFileCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryFileCategoryServiceImpl {

    private final LibraryFileCategoryRepository repository;

    public LibraryFileCategory save(LibraryFileCategory category){

        if(category.getId()!=null){

            LibraryFileCategory dbCategory = repository.findById(category.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Library File Category not found."));

            dbCategory.setFileCategory(category.getFileCategory());
            dbCategory.setShortName(category.getShortName());
            dbCategory.setDescription(category.getDescription());
            dbCategory.setActive(category.getActive());

            dbCategory.setUpdatedBy(category.getUpdatedBy());
            dbCategory.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbCategory);

        }else{

            if(repository.existsByFileCategoryIgnoreCase(category.getFileCategory())){
                throw new RuntimeException("Library File Category already exists.");
            }

            category.setCreatedAt(LocalDateTime.now());

            return repository.save(category);

        }

    }

    public List<LibraryFileCategory> getAll(){

        return repository.findAll();

    }

    public LibraryFileCategory getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Library File Category not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public LibraryFileCategory changeStatus(Long id, Boolean active){

        LibraryFileCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Library File Category not found."));

        category.setActive(active);
        category.setUpdatedAt(LocalDateTime.now());

        return repository.save(category);

    }

}