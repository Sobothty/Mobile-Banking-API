package com.bothty.mobilebankingjpa.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactios")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Account receiver;

    @Column(nullable = false)
    private BigDecimal amount;

    private String remark;

    @ManyToOne
    private TransactionType transactionType;

    private LocalDateTime createdDate;
}
