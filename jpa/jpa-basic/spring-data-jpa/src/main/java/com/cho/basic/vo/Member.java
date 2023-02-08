package com.cho.basic.vo;

import com.cho.basic.vo.enumBundle.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//JPA는 이 객체의 필드와 애노테이션을 기반으로 DDL을 작성하고 DB는 해당 DDL을 통해 테이블을 생성해준다.
//@Entity 붙이기 위한 3가지 조건
//1. 기본 생성자가 꼭 있어야 한다.
//2. final, interface, enum, inner 클래스가 아닌 기본 클래스여야 한다.
//3. 저장할 프로퍼티에 final을 사용할 수 없다.

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER") //테이블 이름을 임의로 정할 수 있음. ORDER같은 mysql에서 테이블 이름으로 못쓰는 명을 고칠 떄 사용하면 유용하다.
public class Member {
    @Id //@Entity의 PK값이 되는 컬럼. 테이블 내에서 유일해야 하고 식별 가능해야 함. (primary key)니까. 외부 FK랑 붙는 놈.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@ID에서 PK값을 고르는 전략을 고르는 @.
//    1. IDENTITY : 기본 키 생성을 현재 연결된 DB에게 위임한다. 계속 값이 채번된다.
//    2. SEQUENCE : DB 시퀀스를 이용해서 기본키를 할당한다. 계속 값이 채번된다.
//    3. TABLE : 키 생성 테이블을 이용한다. 직접 만드는 방식이다.
//    4. AUTO : DB마다 이름이 다르므로 각 DB에 맞게 알아서 IDENTITY, SEQUENCE, TABLE 전략을 선택하게 한다.

    //Q. IDENTITY vs SEQUENCE?
    //IDENTITY 전략은 먼저 엔티티를 DB에 저장하고, 식별자를 조회해서 엔티티의 식별자로 할당하는 전략이다.
    //SEQUENCE 전략은 저장 전에 DB Sequence를 먼저 조회하고, 조회한 식별자를 엔티티에 할당 한 후에
    //엔티티를 persist해서 영속화하고, 이후 트랜잭션이 커밋되어 flush되면 그때 DB에 저장된다.
    @Column(unique = true, nullable = false) //name=""로 이름 지정 가능. @Valid에 걸리는 제약조건 설정 가능. unique, nullable, length 등..
    private Long seq;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 500)
    private String intro;

    @Enumerated(EnumType.STRING) //타입이 클래스가 아닌 enum일 때 사용. 만약, @Enumerated(EnumType.ORDINAL)을 사용하면 그냥 정수값인 0, 1, 2 값으로 들어가게 된다. 보통은 STRING 쓴다. 
    private LoginType loginType;
}