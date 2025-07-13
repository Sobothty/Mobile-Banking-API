package com.bothty.mobilebankingjpa.controller;

import com.bothty.mobilebankingjpa.dto.accountType.AccountTypeRequestDto;
import com.bothty.mobilebankingjpa.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/account-type")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    public ResponseEntity<?> getAllAccountType() {
        return new ResponseEntity<>(
                Map.of(
                        "status", "success",
                        "message", "All Account Type",
                        "data", accountTypeService.getAllAccountType()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> createNewAccountType(@RequestBody AccountTypeRequestDto accountTypeRequestDto){
        return new ResponseEntity<>(
                Map.of(
                        "staus", HttpStatus.CREATED,
                        "message", "AccountType is created successfully",
                        "data", accountTypeService.createAccountType(accountTypeRequestDto)
                ),
                HttpStatus.CREATED
        );
    }
}
