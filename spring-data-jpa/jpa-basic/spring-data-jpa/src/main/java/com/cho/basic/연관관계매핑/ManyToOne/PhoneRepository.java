package com.cho.basic.연관관계매핑.ManyToOne;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> { //interface + extends JpaRepository 붙인게 spring data jpa. 쌩 jpa는 클래스에 EntityManager 받아서 jpql 쿼리 짜서 구현.

}