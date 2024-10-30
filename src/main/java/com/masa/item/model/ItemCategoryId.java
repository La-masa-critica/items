package com.masa.item.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ItemCategoryId {
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "item_id")
    private Long itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCategoryId that)) return false;
        return Objects.equals(getCategoryId(), that.getCategoryId()) &&
                Objects.equals(getItemId(), that.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getItemId());
    }
}
