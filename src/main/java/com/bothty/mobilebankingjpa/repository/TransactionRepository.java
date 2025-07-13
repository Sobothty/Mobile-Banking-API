package com.bothty.mobilebankingjpa.repository;

import com.bothty.mobilebankingjpa.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
