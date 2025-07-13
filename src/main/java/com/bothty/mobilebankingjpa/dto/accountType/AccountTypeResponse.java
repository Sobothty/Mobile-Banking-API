package com.bothty.mobilebankingjpa.dto.accountType;

public record AccountTypeResponse(
        Integer id,
        String name,
        String description,
        String withDrawLimit
) {
}
