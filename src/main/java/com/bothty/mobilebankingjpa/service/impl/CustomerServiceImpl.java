package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.Customer;
import com.bothty.mobilebankingjpa.dto.customer.CreateCustomerRequest;
import com.bothty.mobilebankingjpa.dto.customer.CustomerResponseDto;
import com.bothty.mobilebankingjpa.dto.customer.UpdateCustomerRequestDto;
import com.bothty.mobilebankingjpa.mapper.CustomerMapper;
import com.bothty.mobilebankingjpa.repository.CustomerRepository;
import com.bothty.mobilebankingjpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto createNewCustomer(CreateCustomerRequest createCustomerRequest) {

        if (customerRepository.existsByEmail(createCustomerRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already exists"
            );
        }


        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number is already exists"
            );
        }

        Customer customer = customerMapper.toCustomer(createCustomerRequest);
        customer.setIsDeleted(false);

        customer = customerRepository.save(customer);

        return new CustomerResponseDto(
                customer.getEmail(),
                customer.getFullName(),
                customer.getGender(),
                customer.getRemark()
        );
    }

    @Override
    public List<CustomerResponseDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> customerMapper.fromCustomerToCustomerResponse(customer)
        ).toList();
    }

    @Override
    public CustomerResponseDto findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .map(customer -> customerMapper.fromCustomerToCustomerResponse(customer)
                )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Phone number not found"));
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponseDto updateCustomer(String phoneNumber, UpdateCustomerRequestDto updateCustomerRequestDto) {

        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                ));

        customerMapper.fromCustomerPartially(updateCustomerRequestDto, customer);

        customer = customerRepository.save(customer);

        return customerMapper.fromCustomerToCustomerResponse(customer);
    }
}
