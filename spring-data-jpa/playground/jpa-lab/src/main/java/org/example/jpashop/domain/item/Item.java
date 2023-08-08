package org.example.jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.jpashop.domain.Category;
import org.example.jpashop.exception.NotEnoughStockException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //join말고 성능상 이점 살리려고, 한 테이블에 자식들과 함께 다 때려박음
@DiscriminatorColumn(name = "dtype") //@DiscriminatorColumn은 상속관계에 쓰임. db에서 query로 꺼낼 때, 구분자의 역할.
@Getter
@Setter
public abstract class Item {
    
    @Id
    @GeneratedValue //automatically generate unique primary key values for entity
    @Column(name = "item_id")
    private Long id;
    
    private String name;
    private int price;
    private int stockQuantity;
    
    @ManyToMany(mappedBy = "items") //TODO - entity modeling, 연관관계: Item : Category = N:M, Item : Category_Item : Category = 1 : N : M : 1
    private List<Category> categories = new ArrayList<>();
    
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        
        this.stockQuantity = restStock;
    }
}
