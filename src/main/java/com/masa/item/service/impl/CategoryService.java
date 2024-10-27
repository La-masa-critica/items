package com.masa.item.service.impl;

import com.masa.item.model.Category;
import com.masa.item.repository.CategoryRepository;
import com.masa.item.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Optional<Category> createCategory(Category category) {
        return Optional.of(categoryRepository.save(category));
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
