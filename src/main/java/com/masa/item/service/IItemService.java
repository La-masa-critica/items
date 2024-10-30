package com.masa.item.service;

import com.masa.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    Optional<Item> getItem(Long itemId);

    Optional<Item> createItem(Item item);

    //Item updateItem(Item item);
    Boolean updateStock(Long itemId, Integer quantity);

    Boolean existsByName(String name);

    Boolean existsById(Long itemId);

    List<Item> getItems();

    List<Item> getCategoryItems(List<Long> categoryIds);

    List<Item> getItemsByCategoriesOrPriceRange(List<Long> categoryIds, String minPrice, String maxPrice);

    void deleteItem(Long itemId);
}
