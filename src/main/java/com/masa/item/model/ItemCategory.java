package com.masa.item.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "item_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategory {
    @EmbeddedId
    private ItemCategoryId id = new ItemCategoryId();

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @Column(name = "item_id", insertable = false, updatable = false)
    private Long itemId;
}
