package com.bothty.mobilebankingjpa.mapper;

import com.bothty.mobilebankingjpa.domain.Account;
import com.bothty.mobilebankingjpa.dto.account.AccountResponseDto;
import com.bothty.mobilebankingjpa.dto.account.CreatAccountRequest;
import com.bothty.mobilebankingjpa.dto.account.UpdateAccountRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true) // Ignore database-generated ID
    @Mapping(target = "accountNo", ignore = true) // Ignore if generated after creation
    @Mapping(target = "isDeleted", ignore = true) // Ignore, set by default/logic
    @Mapping(target = "sentTransaction", ignore = true) // Ignore, handled by JPA relationship
    @Mapping(target = "receiveTransaction", ignore = true)
    @Mapping(source = "accountType", target = "accountType.id")
    @Mapping(source = "customer", target = "customer.id")
    Account toAccount(CreatAccountRequest creatAccountRequest);

    @Mapping(source = "customer", target = "customerResponseDto")
    @Mapping(source = "accountType", target = "accountTypeResponse")
    AccountResponseDto toAccountResponse(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "accountType", target = "accountType.id")
    void fromAccountPartially(UpdateAccountRequest updateAccountRequest,
                              @MappingTarget Account account);
}
