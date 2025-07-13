package com.bothty.mobilebankingjpa.dto.customer;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequestDto(
        @NotBlank(message = "FullName is required")
        String fullName,
        @NotBlank(message = "Gender is required")
        String gender,
        String remark
) {
}
