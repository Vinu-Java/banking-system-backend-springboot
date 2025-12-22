package com.bankingsystem.account.controller;

import com.bankingsystem.account.service.AccountServiceInterface;
import com.bankingsystem.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountServiceInterface accountServiceInterface;

    @PostMapping("/transfer")
    public String transfer(@Valid @RequestBody TransferRequestDTO dto) {
        return accountServiceInterface.transfer(dto);
    }
}

