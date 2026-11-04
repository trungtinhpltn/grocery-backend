package com.example.grocery_backend.service;

import com.example.grocery_backend.dto.CategoryRequest;
import com.example.grocery_backend.entity.Category;
import com.example.grocery_backend.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryRequest data) {
        Category ct = new Category();
        ct.setName(data.getName());
        ct.setDescription(data.getDescription());
        return categoryRepository.save(ct);
    }

    public Page<Category> getCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
