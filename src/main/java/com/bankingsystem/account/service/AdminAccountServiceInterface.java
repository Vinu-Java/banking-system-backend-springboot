package com.bankingsystem.account.service;

import com.bankingsystem.dto.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AdminAccountServiceInterface {

    AccountResponseDTO createAccount(AccountCreateRequestDTO dto);
    String updateAccount(Long accountId, UpdateAccountRequestDTO dto);
    String deleteAccount(Long accountId);
    String deposit(DepositRequestDTO dto);
    String withdraw(WithdrawRequestDTO dto);

}
