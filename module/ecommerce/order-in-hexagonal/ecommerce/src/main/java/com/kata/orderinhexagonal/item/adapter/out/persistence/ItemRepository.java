package com.kata.orderinhexagonal.item.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("select i from ItemEntity i left join fetch i.discount where i.id = ?1")
    Optional<ItemEntity> findItemAndDiscountFetchById(Long id);
}
