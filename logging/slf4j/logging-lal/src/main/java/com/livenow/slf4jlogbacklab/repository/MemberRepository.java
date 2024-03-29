package com.livenow.slf4jlogbacklab.repository;

import com.livenow.slf4jlogbacklab.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
}
