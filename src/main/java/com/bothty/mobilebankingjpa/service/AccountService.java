package com.bothty.mobilebankingjpa.service;

import com.bothty.mobilebankingjpa.dto.account.AccountResponseDto;

import java.util.List;

public interface AccountService {
    List<AccountResponseDto> getAllAccount();
}
