package com.cho.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cho.security.model.UserVO;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <UserVO, Long> {

    UserVO findByUserEmailAndUserPw(String userId, String userPw);

    Optional<UserVO> findByUserEmail(String userEmail);
}

