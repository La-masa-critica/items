package com.masa.item.service;

import com.masa.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    Optional<Item> getItem(Long itemId);

    Optional<Item> createItem(Item item);

    //Item updateItem(Item item);
    Optional<Item> updateStock(Long itemId, Integer quantity);

    Optional<Item> incrementStock(Long itemId, Integer quantity);

    Optional<Item> decrementStock(Long itemId, Integer quantity);

    Boolean existsById(Long itemId);

    List<Item> getItems();

    List<Item> getItemsByFilters(List<Long> categoryIds, String minPrice, String maxPrice);

    void deleteItem(Long itemId);
}
