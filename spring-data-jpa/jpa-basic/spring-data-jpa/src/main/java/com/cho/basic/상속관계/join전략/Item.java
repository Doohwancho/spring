package com.cho.basic.상속관계.join전략;

import javax.persistence.*;

/**
 * InheritanceType 종류 3가지
     * 1. JOINED -> 선택!
     * 2. SINGLE_TABLE (default)
     * 3. TABLE_PER_CLASS
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속 구현 전략 선택 -> joined!
@DiscriminatorColumn // 하위 테이블의 구분 컬럼 생성(default = DTYPE)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
}
