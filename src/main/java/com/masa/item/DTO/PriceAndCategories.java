package com.masa.item.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceAndCategories {
    private List<Long> categoryIds;
    private String minPrice;
    private String maxPrice;
}
