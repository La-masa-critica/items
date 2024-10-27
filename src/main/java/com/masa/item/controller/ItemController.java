package com.masa.item.controller;

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
    public ResponseEntity<Item> updateItem(@RequestParam Long itemId, @RequestParam Integer quantity) {
        return itemService.updateStock(itemId, quantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/new")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return itemService.createItem(item)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
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