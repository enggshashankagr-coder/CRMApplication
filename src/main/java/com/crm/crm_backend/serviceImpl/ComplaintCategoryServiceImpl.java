package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.ComplaintCategory;
import com.crm.crm_backend.repository.ComplaintCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
	@RequiredArgsConstructor
	public class ComplaintCategoryServiceImpl {

	    private final ComplaintCategoryRepository repository;

	    public ComplaintCategory save(ComplaintCategory category){

	        if(category.getId()!=null){

	            ComplaintCategory dbCategory = repository.findById(category.getId())
	                    .orElseThrow(() ->
	                            new RuntimeException("Complaint Category not found."));

	            dbCategory.setCategory(category.getCategory());
	            dbCategory.setCategoryDescription(category.getCategoryDescription());
	            dbCategory.setActive(category.getActive());
	            dbCategory.setUpdatedBy(category.getUpdatedBy());
	            dbCategory.setUpdatedAt(LocalDateTime.now());

	            return repository.save(dbCategory);

	        }else{

	            if(repository.existsByCategoryIgnoreCase(category.getCategory())){
	                throw new RuntimeException("Complaint Category already exists.");
	            }

	            category.setCreatedAt(LocalDateTime.now());

	            return repository.save(category);
	        }

	    }

	    public List<ComplaintCategory> getAll(){

	        return repository.findAll();

	    }

	    public ComplaintCategory getById(Long id){

	        return repository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Complaint Category not found."));

	    }

	    public void delete(Long id){

	        repository.deleteById(id);

	    }

	    public ComplaintCategory changeStatus(Long id, Boolean active){

	        ComplaintCategory category = repository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Complaint Category not found."));

	        category.setActive(active);
	        category.setUpdatedAt(LocalDateTime.now());

	        return repository.save(category);

	    }

}
