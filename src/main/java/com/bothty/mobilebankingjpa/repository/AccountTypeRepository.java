package com.bothty.mobilebankingjpa.repository;

import com.bothty.mobilebankingjpa.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    boolean existsByName(String name);
}
