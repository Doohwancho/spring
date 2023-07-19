package me.hajoo.dynamic_insert_update.repository;

import me.hajoo.dynamic_insert_update.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}