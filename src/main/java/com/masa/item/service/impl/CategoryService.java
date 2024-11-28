package com.masa.item.service.impl;

import com.masa.item.model.Category;
import com.masa.item.repository.CategoryRepository;
import com.masa.item.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

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

}
