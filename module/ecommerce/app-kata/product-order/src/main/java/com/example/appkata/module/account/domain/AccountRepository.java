package com.example.appkata.module.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account save(Account account);
    Optional<Account> findByEmail(String email);

    void deleteAll();

    Optional<Account> findById(Long loginUserId);

    void delete(Account user);
}