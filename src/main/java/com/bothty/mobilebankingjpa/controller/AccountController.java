package com.bothty.mobilebankingjpa.controller;


import com.bothty.mobilebankingjpa.dto.account.CreateAccountRequest;
import com.bothty.mobilebankingjpa.dto.account.UpdateAccountRequest;
import com.bothty.mobilebankingjpa.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
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
    }

    @PostMapping
    public ResponseEntity<?> createNewAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){

        return new ResponseEntity<>(
                accountService.createNewAccount(createAccountRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/act-no/{actNo}")
    public ResponseEntity<?> findAccountByAccountNo(@PathVariable String actNo){
        return new ResponseEntity<>(
                Map.of(
                        "data", accountService.findAccountByAccountNo(actNo),
                        "message", "AccountNo is found",
                        "status", "successfully"
                ),HttpStatus.FOUND
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> findAccountByCustomer(@PathVariable Integer customerId){
        return new ResponseEntity<>(
                Map.of(
                        "data", accountService.findAccountByCustomer(customerId),
                        "message", "AccountNo is found",
                        "status", "successfully"
                ),HttpStatus.FOUND
        );
    }

    @DeleteMapping("/{actNo}")
    public ResponseEntity<?> deleteAccountByAccountNo(@PathVariable String actNo){

        accountService.deleteAccountByAccountNo(actNo);

        return new ResponseEntity<>(
                Map.of(
                        "message", "Account is deleted",
                        "status", "success"
                ),HttpStatus.OK
        );
    }

    @PutMapping("/{actNo}")
    public ResponseEntity<?> updateAccountByAccountNo(@PathVariable String actNo, @Valid @RequestBody UpdateAccountRequest updateAccountRequest){
        return new ResponseEntity<>(
                Map.of(
                        "message", "Account is update successfully",
                        "status", "success",
                        "data", accountService.updateAccountByAccountNo(actNo, updateAccountRequest)
                ),HttpStatus.OK
        );
    }

    @PutMapping("/close/{actNo}")
    public ResponseEntity<?> disableAccountByAccountNo(@PathVariable String actNo){
        accountService.disableAccountByAccountNo(actNo);
        return new ResponseEntity<>(
                Map.of(
                        "message", "Account is disable",
                        "status", "success"
                ),HttpStatus.OK
        );
    }
}
