package com.bothty.mobilebankingjpa.dto.account;

import com.bothty.mobilebankingjpa.utils.Currency;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String customerPhoneNumber,
        Integer accountType,
        Currency actCurrency,
        BigDecimal balance
) {
}
