package com.cho.basic.repository.연관관계매핑;

import com.cho.basic.vo.연관관계매핑.OneToOne.MyPage_OneToOne_양방향;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneToOneMyPageRepository extends JpaRepository<MyPage_OneToOne_양방향, Long> { //interface + extends JpaRepository 붙인게 spring data jpa. 쌩 jpa는 클래스에 EntityManager 받아서 jpql 쿼리 짜서 구현.

}
