package org.example.jpashop.repository.queryDSL;


import java.util.List;
import org.example.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom,
    QuerydslPredicateExecutor<Member> {

    List<Member> findByUserName(String userName);
}
