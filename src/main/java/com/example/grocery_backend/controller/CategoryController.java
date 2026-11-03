package com.example.grocery_backend.controller;

import com.example.grocery_backend.entity.Category;
import com.example.grocery_backend.requestdto.CategoryRequest;
import com.example.grocery_backend.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "CategoryController")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping
    public Page<Category> findAll(Pageable pageable) {
        return categoryService.getCategory(pageable);
    }

    @PostMapping()
    public Category create(@RequestBody CategoryRequest data) {
        return categoryService.createCategory(data);
    }
}
