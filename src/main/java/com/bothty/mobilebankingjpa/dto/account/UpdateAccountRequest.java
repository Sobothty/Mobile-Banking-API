package com.bothty.mobilebankingjpa.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateAccountRequest(
        @NotNull(message = "Balance is required")
        BigDecimal balance
) {
}
