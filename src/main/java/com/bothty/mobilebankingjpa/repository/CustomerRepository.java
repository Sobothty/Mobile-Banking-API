package com.bothty.mobilebankingjpa.repository;

import com.bothty.mobilebankingjpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    @Query(value = """
            SELECT EXISTS ( SELECT c.id
                     FROM Customer c
                             WHERE c.phoneNumber = ?1)
            """)
    boolean isExistPhoneNumber(String phoneNumber);

    @Modifying
    @Query(value = """
            UPDATE Customer c SET c.isDeleted = true
                        WHERE c.phoneNumber = ?1
            """)
    void disableCustomerByPhoneNumber(String phoneNumber);

    Optional<Customer> findByPhoneNumberAndIsDeletedIsFalse(String phoneNumber);

    List<Customer> findAllByIsDeletedIsFalse();
}
