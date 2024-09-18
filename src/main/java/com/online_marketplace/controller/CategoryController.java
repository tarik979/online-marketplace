package com.online_marketplace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_marketplace.model.Category;
import com.online_marketplace.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/category")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
       List<Category> categories = categoryService.findAllCategories();
       if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        Category category =  categoryService.findCategoryById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category newCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category updateCategory = categoryService.updateCategory(category);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
