package me.hajoo.rollbackfor.repository;

import me.hajoo.rollbackfor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

