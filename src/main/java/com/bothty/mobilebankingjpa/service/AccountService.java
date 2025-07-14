package com.bothty.mobilebankingjpa.service;

import com.bothty.mobilebankingjpa.dto.account.AccountResponseDto;
import com.bothty.mobilebankingjpa.dto.account.CreateAccountRequest;
import com.bothty.mobilebankingjpa.dto.account.UpdateAccountRequest;
import com.bothty.mobilebankingjpa.dto.customer.CreateCustomerRequest;

import java.util.List;

public interface AccountService {
    AccountResponseDto createNewAccount(CreateAccountRequest createAccountRequest);
    List<AccountResponseDto> getAllAccount();
    AccountResponseDto findAccountByAccountNo(String actNo);
    AccountResponseDto findAccountByCustomer(Integer customerId);
    void deleteAccountByAccountNo(String actNo);
    AccountResponseDto updateAccountByAccountNo(String actNo, UpdateAccountRequest updateAccountRequest);
    void disableAccountByAccountNo(String actNo);
}
