package com.example.hexagonal.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>{

    @Modifying(clearAutomatically = true) //update query 날린 이후, 영속성 컨텍스트 clear()
    @Query(value = "UPDATE Account ac SET ac.balance = :balance where ac.id = :id", nativeQuery = true)
    int updateAccountBalance(Long id, Long balance);
}
