package com.galid.commerce.domains.catalog.domain.item;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.domains.catalog.service.NotEnoughStockQuantityException;
import com.galid.commerce.domains.catalog.domain.category.CategoryEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    private Long categoryId;

    /*

    ItemEntity : CategoryEntity = N : 1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    Q. 이렇게 하면 CategoryEntity를 조회할 때 ItemEntity를 조회하게 되어 성능이 떨어지지 않을까?

    A. Using FetchType.LAZY in the @ManyToOne relationship between ItemEntity and CategoryEntity should not have a
    significant negative impact on performance when querying CategoryEntity. FetchType.LAZY means that JPA will
    only load the related ItemEntity instances when they are explicitly accessed in your code.

    When you fetch a CategoryEntity, JPA will not immediately load the related ItemEntity instances unless you
    explicitly access them (e.g., by calling category.getItems()).
    Instead, JPA will load the related ItemEntity instances on-demand,
    which can help improve performance by avoiding unnecessary data fetching.

    However, if you frequently access the related ItemEntity instances when working with CategoryEntity,
    you may end up with multiple separate queries instead of a single join query,
    which can negatively impact performance. In this case, you may want to consider using a more optimized query,
    such as a JPQL or Criteria API query, that fetches both the CategoryEntity
    and the related ItemEntity instances in a single join query.

    In summary, using FetchType.LAZY with a @ManyToOne relationship should generally not cause
    a significant performance drop when querying CategoryEntity.
    It's essential to analyze your application's data access patterns
    and choose the appropriate fetching strategy based on your specific use cases.



     */

    @Builder
    public ItemEntity(String imagePath, String name, int price, int stockQuantity, Long categoryId) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }

    // ==== 비즈니스 로직 ====
    public void removeStockQuantity(int orderQuantity) {
        int restStockQuantity = this.stockQuantity - orderQuantity;

        if(restStockQuantity < 0)
            throw new NotEnoughStockQuantityException();

        this.stockQuantity = restStockQuantity;
    }

    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }
}
