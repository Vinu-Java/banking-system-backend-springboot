package com.bankingsystem.account.service;

import com.bankingsystem.dto.requestdto.*;
import com.bankingsystem.dto.responsedto.*;


public interface AdminAccountServiceInterface {

    AccountResponseDTO createAccount(AccountCreateRequestDTO dto);

    UpdateAccountResponseDTO updateAccount(Long accountId, UpdateAccountRequestDTO dto);

    void deleteAccount(Long accountId);

    DepositResponseDTO deposit(DepositRequestDTO dto);

    WithdrawResponseDTO withdraw(WithdrawRequestDTO dto);

    BankDashboardResponseDTO getDashboardData();
}
