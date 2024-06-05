package org.example.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //@DiscriminatorColumn은 상속관계에 쓰임. db에서 query로 꺼낼 때, 구분자의 역할.
@Getter @Setter
public class Book extends Item {

    private String author;
    private String isbn;
}
