package com.masa.item.controller;

import com.masa.item.DTO.PriceAndCategories;
import com.masa.item.model.Item;
import com.masa.item.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    private IItemService itemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable Long itemId) {
        return itemService.getItem(itemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateItem(@RequestParam Long itemId, @RequestParam Integer quantity) {
        // Log to check if the method is called
        System.out.println("ItemController.updateItem called");
        return ResponseEntity.ok(itemService.updateStock(itemId, quantity));
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

    @GetMapping("/query")
    public ResponseEntity<List<Item>> getItemsByCategoriesOrPriceRange(@RequestBody PriceAndCategories query) {
        return  ResponseEntity.ok(itemService.getItemsByCategoriesOrPriceRange(
                query.getCategoryIds(),
                query.getMinPrice(),
                query.getMaxPrice()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @Autowired
    public void setItemService(IItemService itemService) {
        this.itemService = itemService;
    }
}
