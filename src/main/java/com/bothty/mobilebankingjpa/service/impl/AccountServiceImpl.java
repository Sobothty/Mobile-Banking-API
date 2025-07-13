package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.Account;
import com.bothty.mobilebankingjpa.dto.account.AccountResponseDto;
import com.bothty.mobilebankingjpa.dto.account.UpdateAccountRequest;
import com.bothty.mobilebankingjpa.mapper.AccountMapper;
import com.bothty.mobilebankingjpa.repository.AccountRepository;
import com.bothty.mobilebankingjpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponseDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::toAccountResponse).toList();
    }

    @Override
    public AccountResponseDto findAccountByAccountNo(String actNo) {
        Account account = accountRepository.findByAccountNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "AccountNo is not found"
                ));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponseDto findAccountByCustomer(Integer customerId) {
        Account account = accountRepository.findByCustomer_Id(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer ID is not found"

                ));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public void deleteAccountByAccountNo(String actNo) {

    }

    @Override
    public AccountResponseDto updateAccountByAccountNo(String actNo, UpdateAccountRequest updateAccountRequest) {
        return null;
    }

    @Override
    public void disableAccountByAccountNo(String actNo) {

    }
}
