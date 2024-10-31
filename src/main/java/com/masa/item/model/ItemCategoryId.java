package com.masa.item.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ItemCategoryId implements Serializable {
    private Long itemId;
    private Long categoryId;
}
