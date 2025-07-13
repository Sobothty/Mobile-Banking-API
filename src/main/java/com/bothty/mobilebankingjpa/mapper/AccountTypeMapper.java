package com.bothty.mobilebankingjpa.mapper;

import com.bothty.mobilebankingjpa.domain.AccountType;
import com.bothty.mobilebankingjpa.dto.accountType.AccountTypeRequestDto;
import com.bothty.mobilebankingjpa.dto.accountType.AccountTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    AccountType fromAccountRequestToAccountType(AccountTypeRequestDto accountTypeRequestDto);
    AccountTypeResponse fromAccountToAccountResponse(AccountType accountType);
}
