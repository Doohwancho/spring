package org.example.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.jpashop.domain.item.Item;

@Entity
@Getter
@Setter
public class Category {
    
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    
    private String name;
    
    //TODO - @ManyToMany, item : category = N:M (중간에 category_item 끼고 1:N:M:1)
    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY) //TODO - self referencing category 구현
    @JoinColumn(name = "parent_id")
    private Category parent;
    
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
    
    public void addChildCategory(Category child) { //이거 잊지마!
        this.child.add(child);
        child.setParent(this);
    }
}
