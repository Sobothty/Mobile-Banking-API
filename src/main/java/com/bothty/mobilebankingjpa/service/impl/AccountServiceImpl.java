package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.Account;
import com.bothty.mobilebankingjpa.domain.AccountType;
import com.bothty.mobilebankingjpa.domain.Customer;
import com.bothty.mobilebankingjpa.domain.Segment;
import com.bothty.mobilebankingjpa.dto.account.AccountResponseDto;
import com.bothty.mobilebankingjpa.dto.account.CreateAccountRequest;
import com.bothty.mobilebankingjpa.dto.account.UpdateAccountRequest;
import com.bothty.mobilebankingjpa.mapper.AccountMapper;
import com.bothty.mobilebankingjpa.repository.AccountRepository;
import com.bothty.mobilebankingjpa.repository.AccountTypeRepository;
import com.bothty.mobilebankingjpa.repository.CustomerRepository;
import com.bothty.mobilebankingjpa.repository.SegmentRepository;
import com.bothty.mobilebankingjpa.service.AccountService;
import com.bothty.mobilebankingjpa.utils.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final SegmentRepository segmentRepository;


    @Override
    public AccountResponseDto createNewAccount(CreateAccountRequest createAccountRequest) {

        Customer customer = customerRepository.findByPhoneNumber(createAccountRequest.customerPhoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Phone Number isn't found"));

        Segment segment = segmentRepository.findBySegmentName(customer.getSegment().getSegmentName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        AccountType accountType = accountTypeRepository.findById(createAccountRequest.accountType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Type isn't found"));

        Account account = new Account();

        switch (createAccountRequest.actCurrency()) {
            case Currency.USD -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(5.00)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be 5$ or more than 5$");
                } else {
                    account.setActCurrency(String.valueOf(createAccountRequest.actCurrency()));
                    account.setBalance(createAccountRequest.balance());
                }
            }
            case Currency.RIEL -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(40000)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be 40000Riel or more than 40000Riel");
                } else {
                    account.setActCurrency(String.valueOf(createAccountRequest.actCurrency()));
                    account.setBalance(createAccountRequest.balance());
                }
            }
        }

        String actNo;

        do {
            actNo = String.format("%09d", new Random().nextInt(1_000_000_000));
        } while (accountRepository.existsByAccountNo(actNo));

        account.setAccountNo(actNo);
        account.setAccountType(accountType);
        account.setIsDeleted(false);

        switch (segment.getSegmentName()) {
            case "REGULAR" -> account.setOverLimit(BigDecimal.valueOf(5000));

            case "SILVER" -> account.setOverLimit(BigDecimal.valueOf(10000));

            case "GOLD" -> account.setOverLimit(BigDecimal.valueOf(50000));
        }

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

        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found")
        );

        Account account = accountRepository.findByCustomer(customer)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer ID is not found"

                ));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public void deleteAccountByAccountNo(String actNo) {
        Account account = accountRepository.findByAccountNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountNo is not found"));

        accountRepository.delete(account);
    }

    @Override
    public AccountResponseDto updateAccountByAccountNo(String actNo, UpdateAccountRequest updateAccountRequest) {

        Account account = accountRepository.findByAccountNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountNo is not found"));

        account.setBalance(updateAccountRequest.balance());

        account = accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public void disableAccountByAccountNo(String actNo) {

        Account account = accountRepository.findByAccountNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        account.setIsDeleted(true);

        accountRepository.save(account);
    }
}
