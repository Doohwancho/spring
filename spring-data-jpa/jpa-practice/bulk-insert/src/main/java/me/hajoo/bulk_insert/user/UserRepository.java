package me.hajoo.bulk_insert.user;

import me.hajoo.bulk_insert.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
