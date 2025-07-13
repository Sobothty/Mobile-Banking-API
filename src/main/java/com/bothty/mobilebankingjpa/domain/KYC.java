package com.bothty.mobilebankingjpa.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "kyc")
public class KYC{

    @Id
    private Integer id;
    private String nationalCardId;
    private Boolean isVerify;
    private Boolean isDeleted;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
