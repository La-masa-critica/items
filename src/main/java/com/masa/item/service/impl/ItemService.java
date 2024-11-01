package com.masa.item.service.impl;

import com.masa.item.model.Item;
import com.masa.item.repository.ItemRepository;
import com.masa.item.service.IItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements IItemService {
    private ItemRepository itemRepository;

    @Override
    @Transactional
    public List<Item> getItemsByFilters(List<Long> categoryIds, String minPrice, String maxPrice) {
        BigDecimal minPriceValue = minPrice != null ? new BigDecimal(minPrice) : null;
        BigDecimal maxPriceValue = maxPrice != null ? new BigDecimal(maxPrice) : null;
        int categoryCount = (categoryIds != null) ? categoryIds.size() : 0;

        return itemRepository.findByCategoriesAndPriceRange(
                categoryIds != null ? categoryIds : List.of(),
                categoryCount,
                minPriceValue,
                maxPriceValue
        );
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

    @Transactional
    @Override
    public Optional<Item> incrementStock(Long itemId, Integer quantity) {
        if (quantity < 0) {
            return Optional.empty();
        }
        return updateStock(itemId, quantity);
    }

    @Transactional
    @Override
    public Optional<Item> decrementStock(Long itemId, Integer quantity) {
        if (quantity < 0) {
            return Optional.empty();
        }
        return updateStock(itemId, -quantity);
    }

    @Transactional
    @Override
    public Optional<Item> updateStock(Long itemId, Integer quantity) {
        return itemRepository.findById(itemId)
                .map(item -> {
                    int newStock = item.getStock() + quantity;
                    if (newStock < 0) {
                        return Optional.<Item>empty();
                    }
                    return Optional.of(itemRepository.save(item.setStock(newStock)));
                }).orElse(Optional.empty());
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
}


