package com.bothty.mobilebankingjpa.repository;

import com.bothty.mobilebankingjpa.domain.Account;
import com.bothty.mobilebankingjpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNo(String accountNo);

    Optional<Account> findByCustomer_Id(Integer customerId);

    boolean existsByAccountNo(String accountNo);

    Optional<Account> findByCustomer(Customer customer);
}
