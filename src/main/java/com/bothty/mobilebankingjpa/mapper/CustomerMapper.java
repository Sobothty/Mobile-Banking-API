package com.bothty.mobilebankingjpa.mapper;


import com.bothty.mobilebankingjpa.domain.Customer;
import com.bothty.mobilebankingjpa.dto.customer.CreateCustomerRequest;
import com.bothty.mobilebankingjpa.dto.customer.CustomerResponseDto;
import com.bothty.mobilebankingjpa.dto.customer.UpdateCustomerRequestDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromCustomerPartially(UpdateCustomerRequestDto updateCustomerRequestDto,
                               @MappingTarget Customer customer);

    CustomerResponseDto fromCustomerToCustomerResponse(Customer customer);
}
