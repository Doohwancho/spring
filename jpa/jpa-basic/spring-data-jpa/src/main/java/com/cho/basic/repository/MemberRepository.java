package com.cho.basic.repository;

import com.cho.basic.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { //interface + extends JpaRepository 붙인게 spring data jpa. 쌩 jpa는 클래스에 EntityManager 받아서 jpql 쿼리 짜서 구현.
    Optional<Member> findByEmail(String email);
    Boolean existsByEmail(String email);
}
