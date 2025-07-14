package com.bothty.mobilebankingjpa.controller;


import com.bothty.mobilebankingjpa.dto.account.CreateAccountRequest;
import com.bothty.mobilebankingjpa.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccount(){
      return new ResponseEntity<>(
              Map.of(
                      "message", "All Account",
                      "status", "success",
                      "data", accountService.getAllAccount()
              ),
              HttpStatus.OK
      );
    };

    @PostMapping
    public ResponseEntity<?> createNewAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        return new ResponseEntity<>(
                Map.of(
                        "message", "Account is created successfully",
                        "status", "success"
                ),
                HttpStatus.CREATED
        );
    }
}
