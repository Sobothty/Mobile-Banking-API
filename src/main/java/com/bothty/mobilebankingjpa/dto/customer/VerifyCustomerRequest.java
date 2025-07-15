package com.bothty.mobilebankingjpa.dto.customer;

public record VerifyCustomerRequest(
        String phoneNumber,
        String nationalCardId
) {
}
