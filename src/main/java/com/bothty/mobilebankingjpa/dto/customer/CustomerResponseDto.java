package com.bothty.mobilebankingjpa.dto.customer;

public record CustomerResponseDto(
        String email,
        String fullName,
        String gender,
        String remark
) {
}
