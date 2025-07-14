package com.bothty.mobilebankingjpa.dto.account;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String customerPhoneNumber,
        Integer accountType,
        String actCurrency,
        BigDecimal balance
) {
}
