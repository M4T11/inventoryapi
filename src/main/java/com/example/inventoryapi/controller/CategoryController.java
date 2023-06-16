package com.example.inventoryapi.controller;

import com.example.inventoryapi.model.Category;
import com.example.inventoryapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="/categories", method= RequestMethod.GET)
    public List<Category> getCategories () {
        return categoryService.getCategories();
    }

    @RequestMapping(value="/categories/id/{category_id}", method= RequestMethod.GET)
    public Category getCategoryById (@PathVariable(value="category_id") Long categoryId) {

        return categoryService.getCategoryById(categoryId);
    }

    @RequestMapping(value="/categories/name/{category_name}", method= RequestMethod.GET)
    public Category getCategoryByName (@PathVariable(value="category_name") String categoryName) {

        return categoryService.getCategoryByName(categoryName);
    }

    @RequestMapping(value="/categories", method= RequestMethod.POST)
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @RequestMapping(value="/categories/id/{category_id}", method= RequestMethod.PUT)
    public Category updateCategory(@PathVariable(value="category_id") Long categoryId, @RequestBody Category category) {
        return categoryService.updateCategory(category, categoryId);
    }

    @RequestMapping(value="/categories/id/{category_id}", method= RequestMethod.DELETE)
    public void deleteCategory (@PathVariable(value="category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


}
