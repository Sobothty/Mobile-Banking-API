package com.bothty.mobilebankingjpa.repository;

import com.bothty.mobilebankingjpa.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository extends JpaRepository<KYC, Integer> {
}
