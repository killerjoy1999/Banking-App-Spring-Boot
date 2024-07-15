package com.example.banking.controller;

import com.example.banking.dto.AccountDto;
import com.example.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //ADD Account  RestApi
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    //GET Account  RestApi

    @GetMapping("/{id}")
    public  ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.OK);
    }
    //Deposit RestApi

    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String , Double> request){
        Double amount =request.get("amount");
        AccountDto accountDto = accountService.deposit(id , amount);
        return ResponseEntity.ok(accountDto);
    }
    //Withdraw RestApi

    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id , amount);
        return ResponseEntity.ok(accountDto);
    }
    //GET ALL ACCOUNTS RESTAPI
    @GetMapping("")
    public  ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts =  accountService.getAllAccounts();
        return  ResponseEntity.ok(accounts);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
