package com.bothty.mobilebankingjpa.dto.customer;

public record CreateCustomerRequest(
        String fullName,
        String gender,
        String email,
        String phoneNumber,
        String remark
) {
}
