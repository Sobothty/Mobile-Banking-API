package com.bothty.mobilebankingjpa.service.impl;

import com.bothty.mobilebankingjpa.domain.Customer;
import com.bothty.mobilebankingjpa.domain.KYC;
import com.bothty.mobilebankingjpa.domain.Segment;
import com.bothty.mobilebankingjpa.dto.customer.CreateCustomerRequest;
import com.bothty.mobilebankingjpa.dto.customer.CustomerResponseDto;
import com.bothty.mobilebankingjpa.dto.customer.UpdateCustomerRequestDto;
import com.bothty.mobilebankingjpa.dto.customer.VerifyCustomerRequest;
import com.bothty.mobilebankingjpa.mapper.CustomerMapper;
import com.bothty.mobilebankingjpa.repository.CustomerRepository;
import com.bothty.mobilebankingjpa.repository.SegmentRepository;
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
    private final SegmentRepository segmentRepository;

    @Override
    public CustomerResponseDto createNewCustomer(CreateCustomerRequest createCustomerRequest) {

        KYC kyc = new KYC();

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

        Segment segment = segmentRepository.findBySegmentName(createCustomerRequest.segment())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment is not found"));

        Customer customer = new Customer();
        customer.setFullName(createCustomerRequest.fullName());
        customer.setGender(createCustomerRequest.gender());
        customer.setEmail(createCustomerRequest.email());
        customer.setPhoneNumber(createCustomerRequest.phoneNumber());
        customer.setRemark(createCustomerRequest.remark());
        customer.setIsDeleted(false);
        customer.setSegment(segment);

        kyc.setNationalCardId(createCustomerRequest.nationalCardId());
        kyc.setIsDeleted(false);
        kyc.setIsVerify(false);
        customer.setKyc(kyc);
        kyc.setCustomer(customer);

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
        List<Customer> customers = customerRepository.findAllByIsDeletedIsFalse();
        return customers.stream().map(customerMapper::fromCustomerToCustomerResponse
        ).toList();
    }

    @Override
    public CustomerResponseDto findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumberAndIsDeletedIsFalse(phoneNumber)
                .map(customerMapper::fromCustomerToCustomerResponse
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

    @Override
    public void disableCustomerByPhoneNumber(String phoneNumber) {
        if (!customerRepository.isExistPhoneNumber(phoneNumber)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Phone Number is not found");
        }

        customerRepository.disableCustomerByPhoneNumber(phoneNumber);
    }

    @Override
    public void verifyCustomer(VerifyCustomerRequest verifyCustomerRequest) {
        Customer customer = customerRepository.findByPhoneNumber(verifyCustomerRequest.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone Number isn't found"));

        KYC kyc = customer.getKyc();
        if (kyc.getNationalCardId().equals(verifyCustomerRequest.nationalCardId())){
            kyc.setIsVerify(true);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "National ID Card isn't Correct");
        }
    }
}
