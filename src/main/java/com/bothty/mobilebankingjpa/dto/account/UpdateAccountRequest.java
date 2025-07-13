package com.bothty.mobilebankingjpa.dto.account;

import jakarta.validation.constraints.NotBlank;

public record UpdateAccountRequest(
        @NotBlank(message = "Account Type is required")
        Integer accountType,
        @NotBlank(message = "Currency is required")
        String actCurrency
) {
}
