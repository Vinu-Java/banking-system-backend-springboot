package com.bankingsystem.account.controller;

import com.bankingsystem.account.service.AccountServiceInterface;
import com.bankingsystem.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountServiceInterface accountServiceInterface;

    @PostMapping("/transfer")
    private String transfer(@Valid @RequestBody TransferRequestDTO dto) {
        return accountServiceInterface.transfer(dto);
    }

    @GetMapping("/balance")
    private String balanceEnquiry(@Valid @RequestBody AccountNumberAndPasswordDTO accountNumberAndPasswordDTO){
        return accountServiceInterface.balanceEnquiry(accountNumberAndPasswordDTO);
    }


}

