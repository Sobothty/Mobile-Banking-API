package com.bothty.mobilebankingjpa.dto.account;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record UpdateAccountRequest(
        @NotBlank
        BigDecimal balance
) {
}
