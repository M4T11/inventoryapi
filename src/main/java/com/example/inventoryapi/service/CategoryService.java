package com.example.inventoryapi.service;

import com.example.inventoryapi.model.Category;
import com.example.inventoryapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        if(!categoryRepository.existsByName(category.getName())) {
            return categoryRepository.save(category);
        } else {
            throw new ResponseStatusException(CONFLICT, "A category with the given name already exists!");
        }
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Category not found!"));
    }

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Category not found!"));
    }

    public Category updateCategory(Category category, Long categoryId) {
        if(categoryRepository.existsById(categoryId)) {
            Category categoryToUpdate = categoryRepository.findById(categoryId).get();
            categoryToUpdate.setName(category.getName());
            return categoryRepository.save(categoryToUpdate);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Category not found!");
        }
    }

    public void deleteCategory(Long categoryId) {
        if(categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Category not found!");
        }

    }


}
