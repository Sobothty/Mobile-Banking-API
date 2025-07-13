package com.bothty.mobilebankingjpa.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transaction_types")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String typeName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean isChargeFee;

    @OneToMany(mappedBy = "transactionType")
    private List<Transaction> transactions;
}
