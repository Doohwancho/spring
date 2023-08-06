package org.example.jpashop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.jpashop.domain.item.Item;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    
    private final EntityManager em;
    
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); //Em.persist() is used to 'store a new entity' in the database.
        } else {
            em.merge(item); //Em.merge() is used to 'update' an existing entity in the database.
        }
    }
    
    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }
    
    public List<Item> findAll() {
        return em.createQuery("select i from Item i",
                Item.class) //jpql이라서 그런지 select * from Item이 아니네? 문법이 다른가보다.
            .getResultList();
    }
}
