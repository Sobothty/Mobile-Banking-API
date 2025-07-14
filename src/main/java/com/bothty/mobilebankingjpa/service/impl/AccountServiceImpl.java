package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.Account;
import com.bothty.mobilebankingjpa.domain.AccountType;
import com.bothty.mobilebankingjpa.domain.Customer;
import com.bothty.mobilebankingjpa.dto.account.AccountResponseDto;
import com.bothty.mobilebankingjpa.dto.account.CreateAccountRequest;
import com.bothty.mobilebankingjpa.dto.account.UpdateAccountRequest;
import com.bothty.mobilebankingjpa.mapper.AccountMapper;
import com.bothty.mobilebankingjpa.repository.AccountRepository;
import com.bothty.mobilebankingjpa.repository.AccountTypeRepository;
import com.bothty.mobilebankingjpa.repository.CustomerRepository;
import com.bothty.mobilebankingjpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public AccountResponseDto createNewAccount(CreateAccountRequest createAccountRequest) {

        Customer customer = customerRepository.findByPhoneNumber(createAccountRequest.customerPhoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Phone Number isn't found"));

        AccountType accountType = accountTypeRepository.findById(createAccountRequest.accountType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Type isn't found"));

        Account account = new Account();
        String actNo;

        do {
            actNo = String.format("%09d", new Random().nextInt(1_000_000_000));
        } while (accountRepository.existsByAccountNo(actNo));

        account.setAccountNo(actNo);
        account.setAccountType(accountType);
        account.setActCurrency(createAccountRequest.actCurrency());
        account.setBalance(createAccountRequest.balance());
        account.setIsDeleted(false);
        account.setCustomer(customer);

        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

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
