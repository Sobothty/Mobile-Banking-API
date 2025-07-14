package com.bothty.mobilebankingjpa.mapper;

import com.bothty.mobilebankingjpa.domain.Account;
import com.bothty.mobilebankingjpa.domain.AccountType;
import com.bothty.mobilebankingjpa.dto.account.AccountResponseDto;
import com.bothty.mobilebankingjpa.dto.account.CreateAccountRequest;
import com.bothty.mobilebankingjpa.dto.account.UpdateAccountRequest;
import com.bothty.mobilebankingjpa.repository.AccountTypeRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "accountType", target = "accountType")
    AccountResponseDto toAccountResponse(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "accountType", target = "accountType.id")
    void fromAccountPartially(UpdateAccountRequest updateAccountRequest,
                              @MappingTarget Account account);

}
