package com.masa.item.service.impl;

import com.masa.item.model.Item;
import com.masa.item.model.ItemCategory;
import com.masa.item.repository.ItemCategoryRepository;
import com.masa.item.repository.ItemRepository;
import com.masa.item.service.IItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemService implements IItemService {
    private ItemRepository itemRepository;
    private ItemCategoryRepository itemCategoryRepository;

    @Transactional
    public List<Item> getItemsByCategoriesOrPriceRange(List<Long> categoryIds, String minPrice, String maxPrice) {
        if (minPrice == null || maxPrice == null) {
            return getCategoryItems(categoryIds);
        }
        if (categoryIds == null) {
            return getByPriceRange(minPrice, maxPrice);
        }
        BigDecimal minPriceBigDecimal = new BigDecimal(minPrice);
        BigDecimal maxPriceBigDecimal = new BigDecimal(maxPrice);
        int categoryCount = categoryIds.size();
        return itemRepository.findItemsByCategoriesAndPriceRange(categoryIds, categoryCount, minPriceBigDecimal, maxPriceBigDecimal);
    }

    @Override
    @Transactional
    public List<Item> getCategoryItems(List<Long> categoryIds) {
        List<ItemCategory> itemCategories = categoryIds.stream()
                .flatMap(categoryId -> itemCategoryRepository.findItemCategoryByCategoryId(categoryId).stream())
                .toList();

        Set<Long> itemIds = itemCategories.stream()
                .collect(Collectors.groupingBy(
                        ItemCategory::getItemId,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == categoryIds.size())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return itemIds.stream()
                .map(itemRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Transactional
    public List<Item> getByPriceRange(String minPrice, String maxPrice) {
        BigDecimal minPriceBigDecimal = new BigDecimal(minPrice);
        BigDecimal maxPriceBigDecimal = new BigDecimal(maxPrice);
        return itemRepository.findByPriceBetween(minPriceBigDecimal, maxPriceBigDecimal);
    }

    @Override
    @Transactional
    public Optional<Item> getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public Optional<Item> createItem(Item item) {
        return Optional.of(itemRepository.save(item));
    }

    @Override
    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    @Transactional
    public Boolean updateStock(Long itemId, Integer quantity) {
        return itemRepository.findById(itemId)
                .map(item -> itemRepository.save(item.setStock(item.getStock() + quantity)))
                .isPresent();
    }

    @Override
    public Boolean existsByName(String name) {
        return itemRepository.findByName(name).isPresent();
    }

    @Override
    public Boolean existsById(Long itemId) {
        return itemRepository.findById(itemId).isPresent();
    }

    @Override
    @Transactional
    public List<Item> getItems() {
        return itemRepository.findByEnabledTrue();
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    public void setItemCategoryRepository(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

}


