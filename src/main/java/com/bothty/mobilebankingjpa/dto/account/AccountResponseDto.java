package com.bothty.mobilebankingjpa.dto.account;

import com.bothty.mobilebankingjpa.dto.accountType.AccountTypeResponse;
import com.bothty.mobilebankingjpa.dto.customer.CustomerResponseDto;

import java.math.BigDecimal;

public record AccountResponseDto(
    String accountNo,
    AccountTypeResponse accountTypeResponse,
    String actCurrency,
    BigDecimal balance,
    CustomerResponseDto customerResponseDto
) {
}
