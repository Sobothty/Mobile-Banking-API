package com.bothty.mobilebankingjpa.dto.account;

import java.math.BigDecimal;

public record CreatAccountRequest(
    Integer customer,
    Integer accountType,
    String actCurrency,
    BigDecimal balance
) {
}
