package com.masa.item.service;

import com.masa.item.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Optional<Category> getCategory(Long categoryId);

    Optional<Category> createCategory(Category category);

    List<Category> getCategories();
}
