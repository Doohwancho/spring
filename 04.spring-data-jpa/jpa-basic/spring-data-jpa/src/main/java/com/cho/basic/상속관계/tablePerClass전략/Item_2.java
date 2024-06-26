package com.cho.basic.상속관계.tablePerClass전략;

import javax.persistence.*;

/**
 * InheritanceType 종류 3가지
 * 1. JOINED -> 선택!
 * 2. SINGLE_TABLE (default)
 * 3. TABLE_PER_CLASS
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 구현 전략 선택 -> joined!
@DiscriminatorColumn // 하위 테이블의 구분 컬럼 생성(default = DTYPE)
public class Item_2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
}
