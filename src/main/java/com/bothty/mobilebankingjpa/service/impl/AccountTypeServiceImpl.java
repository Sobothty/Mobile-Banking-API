package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.AccountType;
import com.bothty.mobilebankingjpa.dto.accountType.AccountTypeRequestDto;
import com.bothty.mobilebankingjpa.dto.accountType.AccountTypeResponse;
import com.bothty.mobilebankingjpa.mapper.AccountTypeMapper;
import com.bothty.mobilebankingjpa.repository.AccountTypeRepository;
import com.bothty.mobilebankingjpa.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public AccountTypeResponse createAccountType(AccountTypeRequestDto accountTypeRequestDto) {

        if (accountTypeRepository.existsByName(accountTypeRequestDto.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This account type name is already exits"
            );
        }

        AccountType accountType = accountTypeMapper.fromAccountRequestToAccountType(accountTypeRequestDto);
        accountType.setIsActive(true);

        accountType = accountTypeRepository.save(accountType);

        return new AccountTypeResponse(
                accountType.getId(),
                accountType.getName(),
                accountType.getDescription()
        );
    }

    @Override
    public List<AccountTypeResponse> getAllAccountType() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypes.stream().map(accountTypeMapper::fromAccountToAccountResponse)
                .toList();
    }
}
