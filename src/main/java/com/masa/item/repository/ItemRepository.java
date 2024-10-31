package com.masa.item.repository;

import com.masa.item.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByEnabledTrue();

    @Query("SELECT i FROM Item i " +
            "JOIN item_category ic ON i.id = ic.item.id " +
            "WHERE (:categoryCount = 0 OR ic.category.id IN :categoryIds) " +
            "AND (:minPrice IS NULL OR i.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR i.price <= :maxPrice) " +
            "AND i.enabled = true " +
            "GROUP BY i.id " +
            "HAVING :categoryCount = 0 OR COUNT(DISTINCT ic.category.id) = :categoryCount")
    List<Item> findByCategoriesAndPriceRange(
            @Param("categoryIds") List<Long> categoryIds,
            @Param("categoryCount") int categoryCount,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );



}
