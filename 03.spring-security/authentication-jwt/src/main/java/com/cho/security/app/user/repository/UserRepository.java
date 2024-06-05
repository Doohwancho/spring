package com.cho.security.app.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cho.security.app.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmailAndPw(String email, String pw);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

