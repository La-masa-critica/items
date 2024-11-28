package com.masa.item.controller;

import com.masa.item.model.Item;
import com.masa.item.service.IItemService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@AllArgsConstructor
public class ItemController {
    private final IItemService itemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable Long itemId) {
        return itemService.getItem(itemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/stock")
    public ResponseEntity<Item> updateStock(@RequestParam Long itemId, @RequestParam Integer quantity) {
        return  itemService.updateStock(itemId, quantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/increment")
    public ResponseEntity<Item> incrementStock(@RequestParam Long itemId, @RequestParam Integer quantity) {
        return itemService.incrementStock(itemId, quantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/decrement")
    public ResponseEntity<Item> decrementStock(@RequestParam Long itemId, @RequestParam Integer quantity) {
        return itemService.decrementStock(itemId, quantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/new")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return itemService.createItem(item)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/exists/{itemId}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.existsById(itemId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Item>> getItemsByCategoriesOrPriceRange(
            @Nullable @RequestParam List<Long> categoryIds,
            @Nullable @RequestParam String minPrice,
            @Nullable @RequestParam String maxPrice) {
        return  ResponseEntity.ok(itemService.getItemsByFilters(
                categoryIds,
                minPrice,
                maxPrice
        ));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }
}
