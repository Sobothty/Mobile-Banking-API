package com.bothty.mobilebankingjpa.controller;

import com.bothty.mobilebankingjpa.dto.customer.CreateCustomerRequest;
import com.bothty.mobilebankingjpa.dto.customer.UpdateCustomerRequestDto;
import com.bothty.mobilebankingjpa.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> findByPhoneNumber(@PathVariable String phoneNumber) {
        return new ResponseEntity<>(
                Map.of(
                        "status", "success",
                        "message", "Phone Number is found",
                        "data", customerService.findByPhoneNumber(phoneNumber)
                ),
                HttpStatus.FOUND
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        return new ResponseEntity<>(Map.of(
                "customers", customerService.getAllCustomer()
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return new ResponseEntity<>(
                Map.of(
                        "data", customerService.createNewCustomer(createCustomerRequest),
                        "message", "Customer is create successfully"
                )
                , HttpStatus.CREATED);
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String phoneNumber) {
        customerService.deleteByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(
                Map.of(
                        "message", "Customer is delete successfully",
                        "status", "success"
                ), HttpStatus.OK
        );
    }


    @PatchMapping("/{phoneNumber}")
    public ResponseEntity<?> updateCustomerByPhoneNumber(@PathVariable String phoneNumber,@Valid @RequestBody UpdateCustomerRequestDto updateCustomerRequestDto) {
        return new ResponseEntity<>(
                Map.of(
                        "data", customerService.updateCustomer(phoneNumber, updateCustomerRequestDto),
                        "status", "Customer is update successfully"
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/disable/{phoneNumber}")
    public ResponseEntity<?> disableCustomerByPhoneNumber(@PathVariable String phoneNumber){
        return new ResponseEntity<>(
                Map.of(
                        "status", "success",
                        "message", "Customer is disable successfully"
                ),HttpStatus.NO_CONTENT
        );
    }
}
