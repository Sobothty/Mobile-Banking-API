package com.bothty.mobilebankingjpa.service;

import com.bothty.mobilebankingjpa.dto.accountType.AccountTypeRequestDto;
import com.bothty.mobilebankingjpa.dto.accountType.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    AccountTypeResponse createAccountType(AccountTypeRequestDto accountTypeRequestDto);
    List<AccountTypeResponse> getAllAccountType();
}
