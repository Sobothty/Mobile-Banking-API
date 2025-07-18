package com.bothty.mobilebankingjpa.service;

import com.bothty.mobilebankingjpa.dto.customer.CreateCustomerRequest;
import com.bothty.mobilebankingjpa.dto.customer.CustomerResponseDto;
import com.bothty.mobilebankingjpa.dto.customer.UpdateCustomerRequestDto;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto createNewCustomer(CreateCustomerRequest createCustomerRequest);
    List<CustomerResponseDto> getAllCustomer();
    CustomerResponseDto findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
    CustomerResponseDto updateCustomer(String phoneNumber, UpdateCustomerRequestDto updateCustomerRequestDto);
}
