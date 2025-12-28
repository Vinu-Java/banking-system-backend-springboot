package com.bankingsystem.account.service;

import com.bankingsystem.dto.requestdto.*;
import com.bankingsystem.dto.responsedto.*;
import jakarta.validation.Valid;


public interface AdminAccountServiceInterface {

    AccountResponseDTO createAccount(AccountCreateRequestDTO dto);

    void updateAccount(UpdateAccountRequestDTO dto);

    void closeAccount(String accountNumber);

    DepositResponseDTO deposit(DepositRequestDTO dto);

    WithdrawResponseDTO withdraw(WithdrawRequestDTO dto);

    BankDashboardResponseDTO getDashboardData();

    AccountNumberValidationResponseDTO validateAccountNumber(@Valid AccountNumberValidationRequestDTO dto);
}
