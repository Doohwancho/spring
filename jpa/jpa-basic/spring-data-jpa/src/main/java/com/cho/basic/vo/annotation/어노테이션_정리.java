package com.cho.basic.vo.annotation;

public class 어노테이션_정리 {
    /**
     * index
     *
     * 1. 매핑 어노테이션
     * 2. 식별자 매핑 어노테이션
     * 3. 권장하는 식별자 전략
     */

    //case1) 매핑 어노테이션
    /*
    -   @Column
        -   @Column(name = "USERNAME")
        -   name
            -   필드와 매핑할 테이블의 컬럼 이름
        -   insertable, updatable
            -   TRUE/FALSE 설정. 읽기 전용
        -   nullable
            -   null 허용여부 결정, DDL 생성시 사용(not null 추가)
        -   unique
            -   유니크 제약 조건, DDL 생성시 사용
        -   columnDefinition, length, precision, scale(DDL)
    -   @Temporal
        -   @Temporal(TemporalType.TIMESTAMP)
        -   시간과 관련된 매핑
        -   Date 뿐만아니라 자바8에서 지원하는 LocalDatetime도 지원한다.
    -   @Enumerates
        -   @Enumerated(EnumType.STRING)
        -   자바의 Enum 타입 매핑을 지원한다.
        -   현업에서는 EnumType을 무조건 STRING으로 지정 해야한다.
        -   기본 값인 ORDINAL로 설정하면 Enum 순서로 숫자가 매핑되는데, Enum 중간에 필드가 하나 추가 되면 다 꼬이게 된다.
    -   @Lob
        -   컨텐츠의 길이가 너무 길 경우 바이너리 파일로 DB에 바로 밀어 넣어야 하는데, 보통 이런 경우에 사용한다.
        -   공통적으로 @Lob으로 사용하면 된다.
        -   CLOB, BLOB 매핑
        -   CLOB : String, char[], java.sql.CLOB
        -   BLOB : byte[], java.sql.BLOB
    -   @Transient
        -   이 필드는 매핑하지 않는다.
        -   애플리케이션에서 DB에 저장하지 않는 필드
        -   웬만하면 쓰지 않는 것이...
     */


    //case2) 식별자 매핑 어노테이션
    /*
        -   @Id(직접 매핑)
    -   @GeneratedValue(strategy = GenerationType.[**타입**])
        -   타입 설정
        -   IDENTITY
            -   데이터베이스에 위임, MYSQL
        -   SEQUENCE
            -   데이터베이스 시퀀스 오브젝트 사용, ORACLE
            -   @SequenceGenerator 필요
        -   TABLE
            -   키 생성용 테이블 사용, 모든 DB에서 사용
            -   @TableGenerator 필요
        -   AUTO
            -   방언에 따라 자동 지정, 기본값
     */

    //case3) 권장하는 식별자 전략
    /*
    -   기본 키 제약 조건
        -   null 아님, 유일, 변하면 안된다.
    -   미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(대체키)를 사용하자.
    -   예를 들어 주민등록번호도 기본 키로 적절하지 않다.
        -   주민번호를 PK로 설정하고 다른 테이블과 FK 참조시 그대로 주민번호가 넘어간다. 갑자기 나라에서 개인정보 목적으로 "DB에 주민번호 저장하지 마라"라고 한다. 헬게이트 오픈
    -   **권장**
        -   **Long + 대체키 + 키 생성전략 사용**
        -   대체키는 전혀 비즈니스랑 관계없는 키.
        -   AUTO_INCREMENT로 숫자로 PK를 사용하면, int쓰면 안된다. 생각보다 금방 끝난다..
        -   Long 타입으로.
     */
}
