package com.bothty.mobilebankingjpa.repository;

import com.bothty.mobilebankingjpa.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
