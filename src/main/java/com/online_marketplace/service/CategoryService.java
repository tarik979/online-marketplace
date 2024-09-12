package com.online_marketplace.service;


import java.util.List;


import org.springframework.stereotype.Service;

import com.online_marketplace.exception.CategoryNotFoundException;
import com.online_marketplace.model.Category;
import com.online_marketplace.repository.CategoryRepository;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
       return categoryRepository.save(category);
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long categoryId){
        return categoryRepository.findById(categoryId)
        .orElseThrow(()-> new CategoryNotFoundException("Category by id " + categoryId + "was not found"));
    }
    
    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }

    public Category findByName(String name){
       return categoryRepository.findByName(name).isPresent() ? categoryRepository.findByName(name).get() : null;
    }
}
