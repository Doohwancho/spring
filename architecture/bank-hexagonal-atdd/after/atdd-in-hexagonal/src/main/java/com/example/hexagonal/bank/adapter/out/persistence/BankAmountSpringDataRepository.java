package com.example.hexagonal.bank.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankAmountSpringDataRepository extends JpaRepository<BankEntity, Long> {
	@Query("SELECT sum(b.balance) FROM BankEntity b WHERE b.customer = ?1 group by b.customer")
	Optional<BigDecimal> getBalance(String customer);
}
