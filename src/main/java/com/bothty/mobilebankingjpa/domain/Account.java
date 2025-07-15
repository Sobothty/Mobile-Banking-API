package com.bothty.mobilebankingjpa.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, length = 32)
    private String accountNo;
    @ManyToOne
    private AccountType accountType;
    @Column(nullable = false, length = 50)
    private String actCurrency;

    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    private Boolean isDeleted;

    private BigDecimal overLimit;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> sentTransaction;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> receiveTransaction;
}
