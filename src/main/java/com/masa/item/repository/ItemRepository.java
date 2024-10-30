package com.masa.item.repository;

import com.masa.item.model.Item;
import com.masa.item.model.ItemCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
    List<Item> findByEnabledTrue();
    List<Item> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);


//    @Query("SELECT i FROM Item i " +
//            "JOIN item_category ic ON i.id = ic.itemId " +
//            "WHERE ic.categoryId IN :categoryIds " +
//            "GROUP BY i.id " +
//            "HAVING COUNT(DISTINCT ic.categoryId) = :categoryCount " +
//            "AND i.price BETWEEN :minPrice AND :maxPrice " +
//            "AND i.enabled = true")
    @Query("SELECT i FROM Item i " +
            "JOIN item_category ic ON i.id = ic.itemId " +
            "WHERE i.id IN (" +
            "    SELECT i.id " +
            "    FROM Item i " +
            "    JOIN item_category ic ON i.id = ic.itemId " +
            "    WHERE ic.categoryId IN :categoryIds " +
            "    GROUP BY i.id " +
            "    HAVING COUNT(DISTINCT ic.categoryId) = :categoryCount " +
            ") " +
            "AND i.price BETWEEN :minPrice AND :maxPrice " +
            "AND i.enabled = true"
    )
    List<Item> findItemsByCategoriesAndPriceRange(
            @Param("categoryIds") List<Long> categoryIds,
            @Param("categoryCount") int categoryCount,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );

    List<Item> findItemsByPriceIsBetweenAndEnabledTrue(BigDecimal minPrice, BigDecimal maxPrice);

}
