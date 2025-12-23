package com.bankingsystem.account.controller;

import com.bankingsystem.account.service.AccountServiceInterface;
import com.bankingsystem.account.service.AdminAccountServiceInterface;
import com.bankingsystem.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminAccountController {

    private final AccountServiceInterface accountService;
    private final AdminAccountServiceInterface adminAccountService;


    @PostMapping("/create")
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountCreateRequestDTO dto) {
        return adminAccountService.createAccount(dto);
    }

    @PutMapping("/update/{accountId}")
    public String update(@PathVariable Long accountId, @Valid @RequestBody UpdateAccountRequestDTO dto) {
        return adminAccountService.updateAccount(accountId, dto);
    }

    @DeleteMapping("/delete/{accountId}")
    public String delete(@PathVariable Long accountId) {
        return adminAccountService.deleteAccount(accountId);
    }


    @PostMapping("/deposit")
    public String deposit(@Valid @RequestBody DepositRequestDTO dto) {
        return adminAccountService.deposit(dto);
    }

    @PostMapping("/transfer")
    public String transfer(@Valid @RequestBody TransferRequestDTO dto) {
        return accountService.transfer(dto);
    }

    @PostMapping("/withdraw")
    public String withdraw(@Valid @RequestBody WithdrawRequestDTO dto) {
        return adminAccountService.withdraw(dto);
    }


}
